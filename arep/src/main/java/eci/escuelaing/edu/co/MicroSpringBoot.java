package eci.escuelaing.edu.co;

import eci.escuelaing.edu.co.annotations.*;
import java.lang.reflect.*;

public class MicroSpringBoot {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Debe pasar el nombre de una clase con @RestController");
            return;
        }

        String controllerClass = args[0];
        Class c = Class.forName(controllerClass);

        if (c.isAnnotationPresent(RestController.class)) {
            Object controllerInstance = c.getDeclaredConstructor().newInstance();

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
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return controllerClass;
                    });
                }
            }
        }

        HttpServer.main(new String[]{});
    }
}
