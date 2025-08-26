package eci.escuelaing.edu.co;

import eci.escuelaing.edu.co.annotations.*;
import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.util.Objects;

public class MicroSpringBoot {
    public static void main(String[] args) throws Exception {
        scanPackage("eci.escuelaing.edu.co.controllers");

        HttpServer.staticfiles("C:/Users/sebas/OneDrive/Escritorio/AREP/AREP-Taller3/arepwww");
        HttpServer.main(new String[]{});
    }

    private static void scanPackage(String basePackage) throws Exception {
        String path = basePackage.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);

        if (resource == null) {
            System.out.println("No se encontró el paquete: " + basePackage);
            return;
        }

        File directory = new File(resource.toURI());
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().endsWith(".class")) {
                String className = basePackage + "." + file.getName().replace(".class", "");
                Class c = Class.forName(className);

                if (c.isAnnotationPresent(RestController.class)) {
                    Object controllerInstance = c.getDeclaredConstructor().newInstance();
                    registerController(c, controllerInstance);
                }
            }
        }
    }

    private static void registerController(Class c, Object controllerInstance) {
        for (Method m : c.getDeclaredMethods()) {
            if (m.isAnnotationPresent(GetMapping.class)) {
                GetMapping mapping = m.getAnnotation(GetMapping.class);

                HttpServer.get(mapping.value(), (req, res) -> {
                    Object[] params = new Object[m.getParameterCount()];
                    int i = 0;
                    for (Parameter p : m.getParameters()) {
                        if (p.isAnnotationPresent(RequestParam.class)) {
                            RequestParam rp = p.getAnnotation(RequestParam.class);
                            String val = req.getQuery(rp.value());
                            if (val == null || val.isEmpty()) val = rp.defaultValue();
                            params[i] = val;
                        }
                        i++;
                    }
                    try {
                        return m.invoke(controllerInstance, params).toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "Error ejecutando método " + m.getName();
                    }
                });
            }
        }
    }
}

