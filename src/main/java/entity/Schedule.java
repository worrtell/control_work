package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Schedule {
    long id;
    long aud_num;
    String time;
    long day_week;
    String teacher;
    String name_group;
}
