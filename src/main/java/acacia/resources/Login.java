package acacia.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.Map;
import static java.util.Collections.singletonMap;

import javax.annotation.security.PermitAll;
import javax.ws.rs.core.MediaType;
import acacia.core.BasicUser;

@Path("/login")
@PermitAll
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class Login{
	
	private final byte[] secretToken;
	
	public Login(byte[] secretToken) {
		this.secretToken = secretToken;
	}
	
	@GET
	@UnitOfWork
	public Map<String, String> login(@Auth BasicUser basicUser) throws JoseException {
		final JwtClaims claim = new JwtClaims();
		claim.setSubject(basicUser.getUsername());
		claim.setStringClaim("username", basicUser.getUsername());
		claim.setStringClaim("role", basicUser.getRole().toString());
		claim.setIssuedAtToNow();
		claim.setGeneratedJwtId();
		claim.setExpirationTimeMinutesInTheFuture(240);
		
		final JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claim.toJson());
		jws.setAlgorithmHeaderValue(HMAC_SHA256);
		jws.setKey(new HmacKey(secretToken));
		
		return singletonMap("token", jws.getCompactSerialization());
	}

}
