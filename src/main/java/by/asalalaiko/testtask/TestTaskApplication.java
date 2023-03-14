package by.asalalaiko.testtask;


import by.asalalaiko.testtask.dto.Ticket;
import by.asalalaiko.testtask.dto.Tickets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;

import java.util.List;

public class TestTaskApplication {
    public static void main(String[] args) {

        try {

            ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();;
            File file = new File("src/main/resources/tickets.json");

            Tickets ticketList = mapper.readValue(file, new TypeReference<Tickets>(){});


            System.out.println("Average: " + ticketList.getAverageTimeTickets().getAsDouble());

            System.out.println("Percentile: " + ticketList.getPercentile(90));


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}
