package com.example.demo.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA="alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA ="-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEAs5aSVydA/4ItxDnOC+nvVKQQemxS/vvtYHkbIs1A+i3nG9rZ\r\n" + 
			"4EjCShfMP6TLf3BQD4gjD62jz0K3nvzYBUunslnisz36vz88X6zu6pDys5wYcSUS\r\n" + 
			"NVVKlOaYczNvn3kgDQu+3vhq5ruIR68tZp1n1g6VIXEl3Qw8yTDQ95HA+hwNomTC\r\n" + 
			"QDLXlRtond0PeXBiWuwkSVVJuFEV/lUqqp1+X4boIIdkZ2aMRZW+sUIX/o9s/z6W\r\n" + 
			"IZSRMT/UHusGvmE5VavCIn84enjm5YASuICc91PGb2vvxR8ahPv4rpdgt8zXLczl\r\n" + 
			"Bs7xLVwmqS+CZc5OINYrldXPtWMS1uaqWmZN6wIDAQABAoIBAQCiCiE+zCdkpBQh\r\n" + 
			"crrMmyaca3CG1cjJU4zquDtY2nRfiB14T665JU1RR0m+vzXyZeeHLCHemqEseJlv\r\n" + 
			"tdpAZPQKA/p+GlVuTGNwPHxSX7jtgNG4/WcBglTKJ4q0n1t/O+5Edy2gx+rFB76+\r\n" + 
			"V3ySk0JGrV+4DfzJB6qtKgPS7ct+n17pd3xQDk24fF7qLlFzAkpUccFFZN2oVsV5\r\n" + 
			"Qx/nvpSqIJL+fnKLS0iJze69+D5DLO2uQgKcnN4sPE0ISDGsEBCzfuZUBkxV13lP\r\n" + 
			"GRip2PyAGOV78Sim7BXj1n5tIIdBFwvLSKu+gw3MDSJkwQtkN+odBtoN+wF5DiPv\r\n" + 
			"uRFor6JpAoGBAOosQE69UZRCM5B2oAVmsXt1EzdookyfKcpCyY8fC02dUK/aFpJY\r\n" + 
			"FdC5aF2TwtPNrnb+vFAfBjik4+oFGYuWT6KaQ7GYX2SP5ATsARxOS+qlnaAqFWuO\r\n" + 
			"b/Kw2EMNSSbo7VKeVNvU8hgHfAX1nGEDsEXIkE+skUOcN3RqD2G3XAFNAoGBAMRT\r\n" + 
			"1xj8Hf+mfDjxlL4L2s6A+eBShKlSkxBjMAfidNuzBhqzsgbzeZEK+U81wPuk8cWe\r\n" + 
			"JnbVD/58qvpYjVXAO8CLrJjUA0eCpXnZlnrdBuwTMzxOq0Ipi9AHK7RVPcXqmtXO\r\n" + 
			"OA77bqacynmxh4k+mnYy+g3QnNFzxQ0IeOJqkvAXAoGARD1k+A0fas7n699Fvo0w\r\n" + 
			"h0OUt/7b3Ow8514TEov/ZTzEuJobHcZhhROioDJhAgvcHX0aL1b7HyKd8CJQUWca\r\n" + 
			"Cc5ej7BRjCKKVgzYPgFrChP9HSFmYJN/d/+k5dVHTfluvza4j988NVhpAeuTCIpk\r\n" + 
			"odFSjIVDR6qMVnZZySX/v7kCgYEAu/XowSrfLZse4wWm+iwvV/VzRai7CcDcu5u6\r\n" + 
			"qFHOv2guN/JCRxVjFdIDzioQkNvh/OjkunBR17tTlkqhRa+9hSI0JD0Wxqf19Sq0\r\n" + 
			"PvzNW8cmDl7i530PNiMnXIcflRR/GKNOWEmwLiN+VfP5ZxRgnTlA3WkUOhM0XL33\r\n" + 
			"vg16kBECgYEApU3Xb6VFWQlWqkQelodcVldq68t7OfwFwDHL1IlKSPauNafn4DQa\r\n" + 
			"W/tyB0/RytKhYJ7ghTmH+3DZokdgoIi3Uhb5tkV6uhxcDuSZgUU6Rb+sVfGD/s1b\r\n" + 
			"ctd3Pbh0MJdWhFXERA0JNuVNgC4WnPpbGo4UwTe6GEAOXCWw2QNx5H0=\r\n" + 
			"-----END RSA PRIVATE KEY-----";

	public static final String RSA_PUBLICA ="-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs5aSVydA/4ItxDnOC+nv\r\n" + 
			"VKQQemxS/vvtYHkbIs1A+i3nG9rZ4EjCShfMP6TLf3BQD4gjD62jz0K3nvzYBUun\r\n" + 
			"slnisz36vz88X6zu6pDys5wYcSUSNVVKlOaYczNvn3kgDQu+3vhq5ruIR68tZp1n\r\n" + 
			"1g6VIXEl3Qw8yTDQ95HA+hwNomTCQDLXlRtond0PeXBiWuwkSVVJuFEV/lUqqp1+\r\n" + 
			"X4boIIdkZ2aMRZW+sUIX/o9s/z6WIZSRMT/UHusGvmE5VavCIn84enjm5YASuICc\r\n" + 
			"91PGb2vvxR8ahPv4rpdgt8zXLczlBs7xLVwmqS+CZc5OINYrldXPtWMS1uaqWmZN\r\n" + 
			"6wIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----\r\n";
}

