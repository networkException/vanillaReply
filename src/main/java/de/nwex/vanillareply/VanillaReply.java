package de.nwex.vanillareply;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import de.nwex.vanillareply.command.ClientCommandManager;
import de.nwex.vanillareply.util.chat.Chat;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.server.command.ServerCommandSource;

public class VanillaReply implements ClientModInitializer {

    public static String lastPlayer = "";

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        ClientCommandManager.addClientSideCommand("r");

        dispatcher.register(literal("r")
            .then(argument("message", StringArgumentType.greedyString()).executes(context -> {
                String message = StringArgumentType.getString(context, "message");

                if(lastPlayer.equals("")) {
                    Chat.error("You haven't messaged anyone yet.");
                    return -1;
                }

                Chat.send(String.format("/msg %s %s", lastPlayer, message));

                return 1;
            })));
    }

    @Override
    public void onInitializeClient() {

    }
}
