package skullition.receptacle;

import net.fabricmc.api.ClientModInitializer;

public class ReceptacleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SetupClient.registerAll();
    }
}
