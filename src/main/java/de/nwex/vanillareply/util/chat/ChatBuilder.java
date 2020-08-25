package de.nwex.vanillareply.util.chat;

import de.nwex.vanillareply.util.TextUtil;
import java.util.Arrays;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.HoverEvent.Action;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

public class ChatBuilder {

    public static Formatting BASE = Formatting.GRAY;
    public static Formatting ACCENT = Formatting.AQUA;
    public static Formatting HIGHLIGHT = Formatting.WHITE;
    public static Formatting DARK = Formatting.DARK_GRAY;
    public static Formatting WARN = Formatting.YELLOW;
    public static Formatting ERROR = Formatting.RED;

    public static MutableText chain(MutableText... texts) {
        return TextUtil.appendAll(text(""), Arrays.asList(texts));
    }

    public static MutableText format(MutableText text, Formatting... formatting) {
        return text.styled(style -> style.withFormatting(formatting));
    }

    public static MutableText text(String string) {
        return new LiteralText(string);
    }

    public static MutableText highlight(String string) {
        return highlight(text(string));
    }

    public static MutableText base(String string) {
        return base(text(string));
    }

    public static MutableText accent(String string) {
        return accent(text(string));
    }

    public static MutableText dark(String string) {
        return dark(text(string));
    }

    public static MutableText error(String string) {
        return error(text(string));
    }

    public static MutableText warn(String string) {
        return warn(text(string));
    }

    public static MutableText highlight(MutableText text) {
        return format(text, HIGHLIGHT);
    }

    public static MutableText base(MutableText text) {
        return format(text, BASE);
    }

    public static MutableText accent(MutableText text) {
        return format(text, ACCENT);
    }

    public static MutableText dark(MutableText text) {
        return format(text, DARK);
    }

    public static MutableText error(MutableText text) {
        return format(text, ERROR);
    }

    public static MutableText warn(MutableText text) {
        return format(text, WARN);
    }

    public static MutableText hover(MutableText text, MutableText hover) {
        return chain(text, () -> text.styled((style) -> style.withHoverEvent(new HoverEvent(Action.SHOW_TEXT, hover))));
    }

    public static MutableText copy(MutableText text, String copy) {
        return chain(text, () -> text.styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, copy))));
    }

    public static MutableText command(MutableText text, String command) {
        return chain(text, () -> text.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command)));
    }

    public static MutableText suggest(MutableText text, String command) {
        return chain(text, () -> text.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command)));
    }

    private static MutableText chain(MutableText text, Runnable action) {
        action.run();

        return text;
    }
}
