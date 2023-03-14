package by.asalalaiko.testtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class Tickets {
    private List<Ticket> tickets;

    @JsonProperty("tickets")
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @JsonProperty("tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    public int getTotalTickets(){
        return tickets.size();
    }

    public Long getDurationTickets(){
        return tickets.stream().mapToLong(o -> o.getFlightTimeToMinutes()).sum();
    }

    public OptionalDouble getAverageTimeTickets(){
        if (getTotalTickets() != 0) {
        Integer durationToInt = getDurationTickets().intValue();

            return tickets.stream().mapToLong(o -> o.getFlightTimeToMinutes()).average();
        }{ return OptionalDouble.of(0);}
    }

    public Long getPercentile(double percentile){
        List<Long> minutesTickets = tickets.stream().mapToLong(o -> o.getFlightTimeToMinutes())
                                            .sorted()
                                            .boxed()
                                            .collect(Collectors.toList());
        int index = (int) Math.ceil(percentile / 100.0 * minutesTickets.size());
        return minutesTickets.get(index - 1);
    }


}
