package com.ab.in.gov.data.api;

import org.springframework.web.bind.annotation.*;

import java.sql.*;

/**
 * Created by abhil on 15-10-2016.
 */
@RestController("train")
public class TrainController {
    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody Train train) throws SQLException, ClassNotFoundException {
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
                String sql = "insert into Train (trainNumber, trainName) values (?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, train.getTrainName());
                    statement.setString(2, train.getTrainNumber());
                    statement.executeUpdate();
                }
            }
            returnString = "Success";
        } catch (Exception ex) {
            throw ex;
        }

        return returnString;
    }
}
