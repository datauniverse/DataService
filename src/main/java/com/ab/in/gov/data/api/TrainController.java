package com.ab.in.gov.data.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhil on 16-10-2016.
 */
@RestController
public class TrainController {
    @RequestMapping(value = "/train", method = RequestMethod.GET)
    public List<Train> trains() throws ClassNotFoundException, SQLException {
        return getTrains();
    }

    @RequestMapping(value = "/train", method = RequestMethod.POST)
    public void addTrain(@RequestBody Train train) throws ClassNotFoundException, SQLException {
        String sql = "insert into TrainInfo (trainCode, trainName) values (?, ?)";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection =
                     DriverManager
                             .getConnection("jdbc:mysql://localhost/ingovdata?user=abhilash&password=Abhi@123")) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, train.getTrainCode());
                statement.setString(2, train.getTrainName());
                statement.executeUpdate();
            }
        }
    }

    private List<Train> getTrains() throws ClassNotFoundException, SQLException {
        String sql = "select trainCode, trainName from TrainInfo";
        List<Train> trains = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection =
                     DriverManager
                             .getConnection("jdbc:mysql://localhost/ingovdata?user=abhilash&password=Abhi@123")) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    trains.add(new Train(resultSet.getString("trainCode"),
                            resultSet.getString("trainName")));
                }
            }
        }
        return trains;
    }
}
