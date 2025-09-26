package it.teammanager.futsalmontevarchi.entity;

import lombok.*;

/*@Entity
@Table(name = "users")*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(unique = true, nullable = false)
	private String username;

//	@Column(nullable = false)
	private String password; // sarà codificata con bcrypt
}