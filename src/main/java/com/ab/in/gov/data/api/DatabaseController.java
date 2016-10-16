package com.ab.in.gov.data.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by abhil on 15-10-2016.
 */
@RestController("database")
public class DatabaseController {
    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create() {
        String returnString = "";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection =
                    DriverManager
                            .getConnection(
                                    "jdbc:postgresql://ec2-54-163-249-150.compute-1.amazonaws.com:5432/dfjima9v1i398l",
                                    "vnguylvzhfgkbb",
                                    "QpKci6mTcXse7z3LpdNsuifGZN"
                            )) {
                String sql =
                        "create table Train ( trainCode varchar(5) not null, trainName varchar(25) not null )";
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(sql);
                }
            }
            returnString = "Success";
        } catch (Exception ex) {
            returnString = "Failure";
        }

        return returnString;
    }
}
