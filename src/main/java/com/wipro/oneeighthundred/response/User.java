package com.wipro.oneeighthundred.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private int id;
    private String title;
    private String body;


/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && id == user.id && Objects.equals(title, user.title) && Objects.equals(body, user.body);
    }*/

  /*  @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, body);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return userId == that.userId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
