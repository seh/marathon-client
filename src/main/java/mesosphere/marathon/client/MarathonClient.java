package mesosphere.marathon.client;

import feign.Feign;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import mesosphere.marathon.client.utils.MarathonException;
import mesosphere.marathon.client.utils.ModelUtils;

public final class MarathonClient {
	private MarathonClient() {
	}

	private static final class MarathonErrorDecoder implements ErrorDecoder {
		@Override
		public Exception decode(String methodKey, Response response) {
			return new MarathonException(response.status(), response.reason());
		}
	}

	public static Marathon getInstance(String endpoint) {
		return getInstance(Feign.builder(), endpoint);
	}

	public static Marathon getInstance(Feign.Builder builder, String endpoint) {
		final Decoder decoder = new GsonDecoder(ModelUtils.GSON);
		final Encoder encoder = new GsonEncoder(ModelUtils.GSON);
		return builder
				.encoder(encoder).decoder(decoder)
				.errorDecoder(new MarathonErrorDecoder())
				.target(Marathon.class, endpoint);
	}
}
