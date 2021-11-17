package hh.Harjoitustyo.Temtem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.Harjoitustyo.Temtem.domain.Move;
import hh.Harjoitustyo.Temtem.domain.MoveRepository;
import hh.Harjoitustyo.Temtem.domain.TemType;
import hh.Harjoitustyo.Temtem.domain.TemTypeRepository;
import hh.Harjoitustyo.Temtem.domain.Temtem;
import hh.Harjoitustyo.Temtem.domain.TemtemRepository;
import hh.Harjoitustyo.Temtem.domain.User;
import hh.Harjoitustyo.Temtem.domain.UserRepository;

@SpringBootApplication
public class TemtemApplication {
	
	private static final Logger log = LoggerFactory.getLogger(TemtemApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(TemtemApplication.class, args);
	}
	
	@Bean
	 public CommandLineRunner temtemDemo(UserRepository userRepo, TemtemRepository temtemRepo, TemTypeRepository typeRepo, MoveRepository moveRepo) {
		return (args) -> {
			
			

			log.info("creating users");
			hh.Harjoitustyo.Temtem.domain.User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepo.save(user1);
			userRepo.save(user2);

			
			log.info("Saving type demo data");
			
			TemType temtype1 = new TemType("Wind");
			TemType temtype2 = new TemType("Neutral");
			TemType temtype3 = new TemType("Fire");
			
			typeRepo.save(temtype1);
			typeRepo.save(temtype2);
			typeRepo.save(temtype3);
			
			log.info("Fetch all types");
			for(TemType temtype : typeRepo.findAll()) {
				log.info(temtype.toString());
			}
			
			Temtem tem1 = new Temtem("Tateru", temtype2);
			Temtem tem2 = new Temtem("Volarend", temtype1);
			Temtem tem3 = new Temtem("Tyranak", temtype3);
			Temtem tem4 = new Temtem("Momo", temtype2);
			
			temtemRepo.save(tem1);
			temtemRepo.save(tem2);
			temtemRepo.save(tem3);
			temtemRepo.save(tem4);
			
			log.info("Fetch all temtems");
			for(Temtem temtem : temtemRepo.findAll()) {
				log.info(temtem.toString());
			}
		
			Move move1 = new Move("Slash" , 50, "Slashes target", temtype2);
			Move move2 = new Move("Fire Spit" , 35, "Spits burning substance", temtype3);
			Move move3 = new Move("Windfan", 24, "Uses a body part to blow wind", temtype1);
			Move move4 = new Move("Another", 12, "Yey", temtype2);
			Move move5 = new Move("Ding", 32, "Wh", temtype2);
			Move move6 = new Move("Dunbg", 34, "Wah", temtype2);
			Move move7 = new Move("Fast", 36, "Woo", temtype2);
			Move move8 = new Move("Test", 38, "Wee", temtype2);
			
			moveRepo.save(move1);
			moveRepo.save(move2);
			moveRepo.save(move3);
			moveRepo.save(move4);
			moveRepo.save(move5);
			moveRepo.save(move6);
			moveRepo.save(move7);
			moveRepo.save(move8);
			log.info("Fetch all moves");
			for(Move move : moveRepo.findAll()) {
				log.info(move.toString());
			}
			
			
		};
		
	}
	
}
