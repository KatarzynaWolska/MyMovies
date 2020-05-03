package com.example.mymovies;

import com.example.mymovies.service.CountryService;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Controller
public class AppController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("/")
    public String homePage(Model model) {
        return "index";
    }

    @RequestMapping("/export")
    public String exportDB() {
        try {
            String connectionURL = "jdbc:mysql://localhost:3306/my_movies_db?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(connectionURL, "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movie");
            String csv = "output.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv));
            List<String[]> data = new ArrayList<String[]>();

            //data.add(new String[] { "movie_id", "length", "title", "director_id" });
            while (rs.next())
            {
                data.add(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) });
            }
            writer.writeAll(data);
            writer.close();

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @RequestMapping("/import")
    public String importDB() {
        Connection con = null;

        try {
            int batchSize = 20;
            String csvFileName = "output.csv";
            String connectionURL = "jdbc:mysql://localhost:3306/my_movies_db?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionURL, "root", "root");
            con.setAutoCommit(false);

            String sql = "INSERT INTO movie (movie_id, length, title, director_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFileName));
            String lineText = null;
            int count = 0;
            lineReader.readLine(); // skip header line

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String movie_id = data[0];
                String length = data[1];
                String title = data[2];
                String director_id = data[3];

                statement.setString(1, movie_id);
                statement.setString(2, length);
                statement.setString(3, title);
                statement.setString(4, director_id);
                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }

        lineReader.close();
        // execute the remaining queries
        statement.executeBatch();
        con.commit();
        con.close();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}
