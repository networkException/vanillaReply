package de.nwex.vanillareply.mixin;

import com.mojang.brigadier.StringReader;

import de.nwex.vanillareply.command.ClientCommandManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo ci) {
        if (message.startsWith("/")) {
            StringReader reader = new StringReader(message);
            reader.skip();

            int cursor = reader.getCursor();

            String commandName = reader.canRead() ? reader.readUnquotedString() : "";
            reader.setCursor(cursor);

            if (ClientCommandManager.isClientSideCommand(commandName)) {
                ClientCommandManager.executeCommand(reader, message);
                ci.cancel();
            }
        }
    }
}
