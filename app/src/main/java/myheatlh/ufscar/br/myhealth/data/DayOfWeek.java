package myheatlh.ufscar.br.myhealth.data;

public enum DayOfWeek {
    EVERYDAY("todos os dias", 0),
    SUNDAY("domingo", 1),
    MONDAY("segunda", 2),
    TUESDAY("terça", 3),
    WEDNESDAY("quarta", 4),
    THURSDAY("quinta", 5),
    FRIDAY("sexta",6),
    SATURDAY("sábado", 7);

    String dayOfWeek;
    Integer day;
    DayOfWeek(String dayOfWeek, Integer day){
        this.dayOfWeek = dayOfWeek;
        this.day = day;
    }
}
