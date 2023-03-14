package by.asalalaiko.testtask.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Getter
@ToString
public class Ticket {
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private String carrier;
    private Integer stops;
    private BigDecimal price;

    @JsonCreator
    public Ticket(@JsonProperty("origin") String origin,
                  @JsonProperty("origin_name") String originName,
                  @JsonProperty("destination") String destination,
                  @JsonProperty("destination_name") String destinationName,
                  @JsonProperty("departure_date") String departureDate,
                  @JsonProperty("departure_time") String departureTime,
                  @JsonProperty("arrival_date") String arrivalDate,
                  @JsonProperty("arrival_time") String arrivalTime,
                  @JsonProperty("carrier") String carrier,
                  @JsonProperty("stops") int stops,
                  @JsonProperty("price") int price){

        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.departure = getDateTime(departureDate,departureTime);
        this.arrival = getDateTime(arrivalDate, arrivalTime);
        this.carrier = carrier;
        this.stops = stops;
        this.price = BigDecimal.valueOf(price);
    }


    public  LocalDateTime getDateTime(String stringDate, String stringTime){

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().parseLenient()
                .appendPattern("[dd.MM.yy][d.MM.yy][d.M.yy]")
                .appendPattern("[HH:mm][H.mm]");

        DateTimeFormatter formatter = builder.toFormatter();

       LocalDate datePart = LocalDate.parse(stringDate, formatter);
       LocalTime timePart = LocalTime.parse(stringTime, formatter);

        LocalDateTime resultDateTime = LocalDateTime.of(datePart, timePart);

        return resultDateTime;
    }

    public Long getFlightTimeToMinutes(){
        Duration duration = null;

        try {
               duration = Duration.between(this.departure, this.arrival);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duration.toMinutes();
    }


}
