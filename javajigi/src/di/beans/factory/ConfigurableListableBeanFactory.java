package di.beans.factory;

public interface ConfigurableListableBeanFactory extends BeanFactory {
    void preInstantiateSinglonetons();
}
