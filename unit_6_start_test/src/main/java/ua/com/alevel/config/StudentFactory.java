package ua.com.alevel.config;

import org.reflections.Reflections;
import ua.com.alevel.dao.StudentDao;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StudentFactory {

    private Reflections reflections = new Reflections("ua.com.alevel");
    private static Map<Class<?>,Object> map = new HashMap<>();

    public void configure() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<Class<? extends StudentDao>> subTypesOf = reflections.getSubTypesOf(StudentDao.class);
        for (Class<? extends StudentDao> aClass : subTypesOf) {
            if (aClass.isAnnotationPresent(Release.class)) {
                System.out.println("aClass = " + aClass);
                map.put(StudentDao.class, aClass.getDeclaredConstructor().newInstance());
            }
        }
    }

    public static Object getImplementationByClass(Class<?> aClass) {
        return map.get(aClass);
    }
}
