package com.yurisilva.mundialechallenge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "TBL_AUTHORITY")
@Getter
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name", length = 15, unique = true)
    private String name;

    @ManyToMany(mappedBy = "authorities")
    @JsonBackReference
    private Set<User> user;


    public static final class Builder {
        private Long id;
        private String name;
        private Set<User> user;

        private Builder() {
        }

        public static Builder anAuthority() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUser(Set<User> user) {
            this.user = user;
            return this;
        }

        public Authority build() {
            Authority authority = new Authority();
            authority.setId(id);
            authority.setName(name);
            authority.setUser(user);
            return authority;
        }
    }
}
