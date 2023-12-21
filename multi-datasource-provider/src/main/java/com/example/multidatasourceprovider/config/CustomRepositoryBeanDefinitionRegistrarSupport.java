package com.example.multidatasourceprovider.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.repository.config.*;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;


public abstract class CustomRepositoryBeanDefinitionRegistrarSupport
    implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    private @SuppressWarnings("null") @NonNull ResourceLoader resourceLoader;
    private @SuppressWarnings("null") @NonNull Environment environment;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Forwarding to {@link #registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry, BeanNameGenerator)} for
     * backwards compatibility reasons so that tests in downstream modules do not accidentally invoke the super type's
     * default implementation.
     *
     * @see org.springframework.context.annotation.ImportBeanDefinitionRegistrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata,
     *      org.springframework.beans.factory.support.BeanDefinitionRegistry)
     * @deprecated since 2.2, call
     *             {@link #registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry, BeanNameGenerator)}
     *             instead.
     * @see ConfigurationClassPostProcessor#IMPORT_BEAN_NAME_GENERATOR
     */
    @Override
    @Deprecated
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        registerBeanDefinitions(metadata, registry, ConfigurationClassPostProcessor.IMPORT_BEAN_NAME_GENERATOR);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry,
                                        BeanNameGenerator generator) {

        Assert.notNull(metadata, "AnnotationMetadata must not be null");
        Assert.notNull(registry, "BeanDefinitionRegistry must not be null");
        Assert.notNull(resourceLoader, "ResourceLoader must not be null");

        // Guard against calls for sub-classes
        if (metadata.getAnnotationAttributes(getAnnotation().getName()) == null) {
            return;
        }

        CustomAnnotationRepositoryConfigurationSource configurationSource = new CustomAnnotationRepositoryConfigurationSource(metadata,
            getAnnotation(), resourceLoader, environment, registry, generator);

        RepositoryConfigurationExtension extension = getExtension();
        RepositoryConfigurationUtils.exposeRegistration(extension, registry, configurationSource);

        RepositoryConfigurationDelegate delegate = new RepositoryConfigurationDelegate(configurationSource, resourceLoader,
            environment);

        delegate.registerRepositoriesIn(registry, extension);
    }

    /**
     * Return the annotation to obtain configuration information from. Will be wrappen into an
     * {@link AnnotationRepositoryConfigurationSource} so have a look at the constants in there for what annotation
     * attributes it expects.
     *
     * @return
     */
    protected abstract Class<? extends Annotation> getAnnotation();

    /**
     * Returns the {@link RepositoryConfigurationExtension} for store specific callbacks and {@link BeanDefinition}
     * post-processing.
     *
     * @see RepositoryConfigurationExtensionSupport
     * @return
     */
    protected abstract RepositoryConfigurationExtension getExtension();
}
