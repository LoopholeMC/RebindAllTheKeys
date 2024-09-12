package com.minenash.rebind_all_the_keys.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minenash.rebind_all_the_keys.RebindAllTheKeys;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {

    @WrapOperation(method = "mouseReleased", at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;hasShiftDown()Z"))
    public boolean rebindShift(Operation<Boolean> original) {
        return RebindAllTheKeys.QUICK_MOVE.isDefault() ? original.call() : RebindAllTheKeys.isKeybindPressed(RebindAllTheKeys.QUICK_MOVE);
    }

    @WrapOperation(method = "mouseReleased", at = @At(value = "INVOKE",target = "Lnet/minecraft/client/util/InputUtil;isKeyPressed(JI)Z"))
    public boolean rebindShift2(long handle, int code, Operation<Boolean> original) {
        return RebindAllTheKeys.QUICK_MOVE.isDefault() ? original.call(handle, code) : RebindAllTheKeys.isKeybindPressed(RebindAllTheKeys.QUICK_MOVE);
    }

    @WrapOperation(method = "mouseClicked", at = @At(value = "INVOKE",target = "Lnet/minecraft/client/util/InputUtil;isKeyPressed(JI)Z"))
    public boolean rebindShift3(long handle, int code, Operation<Boolean> original) {
        return RebindAllTheKeys.QUICK_MOVE.isDefault() ? original.call(handle, code) : RebindAllTheKeys.isKeybindPressed(RebindAllTheKeys.QUICK_MOVE);
    }

    @WrapOperation(method = "keyPressed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;hasControlDown()Z"))
    public boolean rebindDropStackModifier(Operation<Boolean> original) {
        return RebindAllTheKeys.DROP_STACK_MODIFIER.isDefault() ? original.call() : RebindAllTheKeys.isKeybindPressed(RebindAllTheKeys.DROP_STACK_MODIFIER);
    }

}
