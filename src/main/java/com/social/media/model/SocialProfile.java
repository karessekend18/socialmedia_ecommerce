package com.social.media.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_user")
    private SocialUser user;
}
