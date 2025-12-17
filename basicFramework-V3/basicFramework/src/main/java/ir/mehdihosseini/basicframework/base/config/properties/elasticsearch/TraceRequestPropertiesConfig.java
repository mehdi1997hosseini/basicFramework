package ir.mehdihosseini.basicframework.base.config.properties.elasticsearch;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TraceRequestPropertiesConfig {

    private InputRequests inputRequests;
    private ThirdParty thirdParty;

    @Getter
    @Setter
    public static class InputRequests {
        private Boolean isEnable = false;
    }

    @Getter
    @Setter
    public static class ThirdParty {
        private Boolean isEnable = false;
    }

}
