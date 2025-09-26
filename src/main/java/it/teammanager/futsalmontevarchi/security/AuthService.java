package it.teammanager.futsalmontevarchi.security;

//@Service
public class AuthService {
/*	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public AuthService(UserRepository userRepository,
					   PasswordEncoder passwordEncoder,
					   JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public LoginResponse login(LoginRequest request) {
		return userRepository.findByUsername(request.username())
					   .filter(user -> passwordEncoder.matches(request.password(), user.getPassword()))
					   .map(user -> {
						   String token = jwtUtil.generateToken(user.getUsername());
						   return new LoginResponse(true, token, "Login successful");
					   })
					   .orElse(new LoginResponse(false, null, "Invalid credentials"));
	}

	public User register(String username, String password) {
		User user = User.builder()
							.username(username)
							.password(passwordEncoder.encode(password))
							.build();
		return userRepository.save(user);
	}*/
}
