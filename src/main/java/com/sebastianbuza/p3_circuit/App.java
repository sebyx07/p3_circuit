package com.sebastianbuza.p3_circuit;

import com.sebastianbuza.p3_circuit.mappers.PointMapper;
import com.sebastianbuza.p3_circuit.models.point.Point;
import com.sebastianbuza.p3_circuit.stores.PointStorage;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class App {

    @Autowired
    PointStorage pointStorage;

    @Autowired
    PointMapper pointMapper;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        App app = (App) context.getBean("app");

        try {
            File file = new File(app.getClass().getClassLoader().getResource(
                    File.separator + "json" + File.separator + "example.json").toURI()
            );
            app.getPointMapper().map(new FileReader(file));

            app.getPointStorage().getStore().get("g4").isTrue();

            for(Map.Entry<String, Point> entry : app.getPointStorage().getStore().entrySet()){
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public App(){}

    public App(PointMapper mapper, PointStorage storage){
        this.pointMapper = mapper;
        this.pointStorage = storage;
    }

    public PointStorage getPointStorage() {
        return pointStorage;
    }

    public PointMapper getPointMapper() {
        return pointMapper;
    }
}
