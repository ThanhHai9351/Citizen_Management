/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qlcongdan;

/**
 *
 * @author Admin
 */
import org.neo4j.driver.*;

public class Neo4jConnection {
    private final Driver driver;

    public Neo4jConnection() {
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
    }

    public Session getSession() {
        return driver.session(); // Mở session mới
    }

    public void close() {
        driver.close(); // Đóng driver khi không cần dùng nữa
    }
}


