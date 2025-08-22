package com.social.media;

import com.social.media.model.Post;
import com.social.media.model.SocialGroup;
import com.social.media.model.SocialProfile;
import com.social.media.model.SocialUser;
import com.social.media.repository.PostRepository;
import com.social.media.repository.SocialGroupRepository;
import com.social.media.repository.SocialProfileRepository;
import com.social.media.repository.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    private final SocialUserRepository userRepository;
    private final SocialProfileRepository profileRepository;
    private final SocialGroupRepository groupRepository;
    private final PostRepository postRepository;

    public DataInitializer(SocialUserRepository userRepository, SocialProfileRepository profileRepository, SocialGroupRepository groupRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.groupRepository = groupRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();

            group1.getSocialUsers().add(user1);
            group1.getSocialUsers().add(user2);

            group2.getSocialUsers().add(user1);
            group2.getSocialUsers().add(user2);

            groupRepository.save(group1);
            groupRepository.save(group2);

            user1.getGroups().add(group1);
            user2.getGroups().add(group1);
            user2.getGroups().add(group2);
            user3.getGroups().add(group2);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);


            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            post1.setSocialUser(user1);
            post2.setSocialUser(user2);
            post3.setSocialUser(user3);

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            profile1.setUser(user1);
            profile2.setUser(user2);
            profile3.setUser(user3);

            profileRepository.save(profile1);
            profileRepository.save(profile2);
            profileRepository.save(profile3);

        };
    }
}
