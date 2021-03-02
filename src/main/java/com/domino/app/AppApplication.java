package com.domino.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SpringBootApplication
public class AppApplication extends JFrame implements CommandLineRunner {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private Game game;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
       
    }

    @Override
    public void run(String... args) throws Exception {
        game.play();
    }

}
