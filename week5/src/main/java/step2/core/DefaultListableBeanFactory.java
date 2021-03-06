package step2.core;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DefaultListableBeanFactory extends AbstractBeanFactory
		implements BeanDefinitionRegistry, ListableBeanFactory {

	private Map<String, BeanDefinition> beanDefinitionHash = new HashMap();

	@Override
	public String[] getBeanDefinitionNames() {
		Set keys = beanDefinitionHash.keySet();
		String[] names = new String[keys.size()];
		Iterator itr = keys.iterator();
		int i = 0;
		while (itr.hasNext()) {
			names[i++] = (String) itr.next();
		}
		return names;
	}

	@Override
	public int getBeanDefinitionCount() {
		return beanDefinitionHash.size();
	}

	protected void preInstantiate() throws NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		String[] beanNames = getBeanDefinitionNames();
		for (int i = 0; i < getBeanDefinitionCount(); i++) {
			getBean(beanNames[i]);
		}
	}

	public DefaultListableBeanFactory(BeanFactory parentBeanFactory) {
		super(parentBeanFactory);
	}

	@Override
	public void registerBeanDefinition(String id, BeanDefinition beanDefinition) {
		beanDefinitionHash.put(id, beanDefinition);
	}

	@Override
	public BeanDefinition getBeanDefinition(String key) {
		return beanDefinitionHash.get(key);
	}
	
	@Override
	public void clear() {
		beanDefinitionHash.clear();
		super.clearBeanHash();
	}
}
