package com.rzuwik.rb.classes;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CustomClassLoader {


      public static void loadClassFromURL(String url,String classdirectory) throws MalformedURLException, ClassNotFoundException {
          final ClassLoader classLoader = new URLClassLoader(new URL[]{
                  new URL(url)
          }, CustomClassLoader.class.getClassLoader());
          Class<?> someClass = Class.forName(classdirectory, true, classLoader);
      }

    public static Map<Class<?>, byte[]> getLoadedClasses() {
        final Map<Class<?>, byte[]> returnMap = new HashMap<>();
        try {
            final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            final Field classesField = ClassLoader.class.getDeclaredField("classes");
            classesField.setAccessible(true);
            final Vector<Class<?>> classes = (Vector<Class<?>>) classesField.get(classLoader);
            for (Class<?> classFor : classes) {
                final String className = classFor.getName().replace('.', '/');
                final URL location = classFor.getResource('/' + className + ".class");
                final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                final InputStream inputStream = location.openStream();
                // If you have a potato computer you might want to consider turning this down.
                byte[] buffer = new byte[1024 * 4];
                int chunk;
                while ((chunk = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, chunk);
                }
                returnMap.put(classFor, outputStream.toByteArray());
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return returnMap;
    }
}
