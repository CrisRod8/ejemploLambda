package org.example;

import java.util.HashMap;
import java.util.Map;

public class ejemplolambda {
    static Map<String, Servicio1Param> servicios = new HashMap<String, Servicio1Param>();

    public static void main(String[] args) {
        System.out.println("Hello World");
        registrar("/hello", str -> "Hello " + str);
        System.out.println(buscar("/hello").handle("Cristian"));
        registrar("/cuadrado", str -> str + " al cuadrado es: " + Math.pow(Integer.parseInt(str), 2));
        System.out.println(buscar("/cuadrado").handle("4"));

        registrar("/cos", new Servicio1Param() {
            @Override
            public String handle(String str) {
                return "" + Math.cos(Double.parseDouble(str));
            }
        });
        System.out.println("El cos de 0.77 rad es: " + buscar("/cos").handle("0.77"));

        registrar("/sin", str -> {
            Double val = Double.parseDouble(str.split("val=")[1]);
            return "" + Math.sin(val);
        });
        System.out.println("El sin de  0.55 es: " + buscar("/sin").handle("/sin?val=0.55"));

    }

    public static void registrar(String url, Servicio1Param endpoint){
        servicios.put(url,endpoint);
    }

    public static Servicio1Param buscar(String url){
        return servicios.get(url);
    }
}