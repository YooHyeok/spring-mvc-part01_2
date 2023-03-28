package hello.spring.basic;

import lombok.Data;

@Data //Getter, Setter, ToString, EqualsAndHashCode, RequesreArgsConstructor 자동 적용
public class HelloData {
    private String username;
    private int age;
}
