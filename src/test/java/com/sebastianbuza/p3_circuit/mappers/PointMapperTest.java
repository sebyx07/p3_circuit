package com.sebastianbuza.p3_circuit.mappers;

import com.sebastianbuza.p3_circuit.models.point.Gateway;
import com.sebastianbuza.p3_circuit.models.point.Point;
import com.sebastianbuza.p3_circuit.stores.PointStorage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config.xml"})
public class PointMapperTest {

    @Autowired
    PointMapper pointMapper;

    @Autowired
    PointStorage pointStorage;

    private File file;

    @Before
    public void setUp() throws Exception{
        file = new File(ClassLoader.getSystemResource(File.separator + "example.json").toURI());
    }

    @Test
    public void testMap() throws Exception {
        pointMapper.map(new FileReader(file));
        Point g3 = pointStorage.getStore().get("g3");
        assertTrue(g3.isTrue());
    }
}