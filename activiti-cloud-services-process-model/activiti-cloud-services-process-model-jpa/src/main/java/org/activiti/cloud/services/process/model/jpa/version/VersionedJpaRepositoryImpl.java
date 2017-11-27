/*
 * Copyright 2017 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.cloud.services.process.model.jpa.version;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.EntityManager;

import org.activiti.cloud.services.process.model.core.version.VersionEntity;
import org.activiti.cloud.services.process.model.core.version.VersionGenerator;
import org.activiti.cloud.services.process.model.core.version.VersionIdentifier;
import org.activiti.cloud.services.process.model.core.version.VersionedEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation for {@link VersionedJpaRepository}
 */
public class VersionedJpaRepositoryImpl<T extends VersionedEntity, ID extends Serializable, V extends VersionEntity>
        extends SimpleJpaRepository<T, ID>
        implements VersionedJpaRepository<T, ID, V> {

    private Class<T> versionedClass;

    private Class<V> versionClass;

    private VersionGenerator versionGenerator = new VersionGenerator();

    /**
     * Creates a new {@link SimpleJpaRepository} to manage objects of the given domain type.
     * @param versionedClass the class of the version entity.
     * @param versionClass the class of the version entity.
     * @param entityManager must not be {@literal null}.
     */
    public VersionedJpaRepositoryImpl(final Class<T> versionedClass,
                                      final Class<V> versionClass,
                                      final EntityManager entityManager) {
        super(versionedClass,
              entityManager);
        this.versionedClass = versionedClass;
        this.versionClass = versionClass;
    }

    /**
     * Add a new version before any save.
     * @param versionedEntity the entity to save
     * @param <S> the versionedEntity type
     * @return the saved entity
     */
    @Override
    @Transactional
    public <S extends T> S save(S versionedEntity) {
        generateNextVersion(versionedEntity);
        return super.save(versionedEntity);
    }

    /**
     * Generate and add a new version to a given version entity.
     * @param versionedEntity the version entity to generate for
     */
    protected void generateNextVersion(T versionedEntity) {
        String nextVersion = versionGenerator
                .generateNextVersion(Optional.ofNullable(versionedEntity.getLatestVersion()));

        try {
            VersionEntity newVersion = versionClass.newInstance();
            newVersion.setVersionedEntity(versionedEntity);
            newVersion.setVersionIdentifier(new VersionIdentifier(versionedEntity.getId(),
                                                                   nextVersion));

            versionedEntity.fillVersionContent(newVersion);
            if (versionedEntity.getVersions() == null) {
                versionedEntity.setVersions(new ArrayList<>());
            }
            versionedEntity.getVersions().add(newVersion);
            versionedEntity.setLatestVersion(newVersion);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new DataIntegrityViolationException(
                    String.format("Cannot add a new version of type %s for version entity type %s",
                                  versionClass,
                                  versionedClass),
                    e);
        }
    }
}
