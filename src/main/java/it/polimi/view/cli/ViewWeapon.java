package it.polimi.view.cli;

import it.polimi.model.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewWeapon {
    
    ViewCLI viewCLI;
    
    public ViewWeapon(ViewCLI viewCLI){
        
        this.viewCLI = viewCLI;
        
    }
    
    //weapon method
    
    public void viewSelectWeapon() throws RemoteException {
    
        if(viewCLI.checkCurrent()) {
            
            ArrayList<WeaponCard> weapons = viewCLI.gameModel.getActualPlayer().getPlayerBoard().getPlayerWeapons();
            PrintEffectWeapon.printSelectWeapon();
            PrintWeapon.printName(weapons);
            
            viewCLI.setIndex(viewCLI.getUserInput(0,weapons.size()));
            viewCLI.notifyController();
        } else {
    
            PrintNotActualMenu.printMenu(viewCLI.gameModel,viewCLI);
        }
        
    }
    
    public void viewSelectWeaponEffect() throws RemoteException {
    
        if(viewCLI.checkCurrent()) {
        
            PrintEffectWeapon.printSelectWeaponEffect();
            PrintWeapon.printEffectName(viewCLI.gameModel.getAvailableEffect());
        
            viewCLI.setIndex2(viewCLI.getUserInput(-2,viewCLI.gameModel.getAvailableEffect().size()));
            viewCLI.notifyController();
        } else {
    
            PrintNotActualMenu.printMenu(viewCLI.gameModel,viewCLI);
        }
        
        
    }
    
    public void viewShoot() throws RemoteException {
    
        if(viewCLI.checkCurrent()) {
            
            System.out.println("\n\n\n\n");
    
            System.out.println(viewCLI.gameModel.getState().toString());
    
            viewCLI.printMessageAll();
    
            if (viewCLI.gameModel.getPlayerDamaged().size() > 0) {
        
                System.out.println("\n DAMAGED PLAYER IN THIS SHOOT:\n");
        
                for (Player a : viewCLI.gameModel.getPlayerDamaged()) {
            
                    PrintPlayerBoard.print(a);
                }
            }
    
            if (viewCLI.gameModel.getPlayerMarked().size() > 0) {
        
                System.out.println("\n MARKED PLAYER IN THIS SHOOT:\n");
        
                for (Player a : viewCLI.gameModel.getPlayerMarked()) {
            
                    PrintPlayerBoard.print(a);
                }
            }
    
            if (viewCLI.gameModel.isWantToUseTargeting()){
    
                System.out.println("YOU HAVE DISPONIBLE TAGETING SCOPE\n DO YOU WAN TO USE IT");
                viewCLI.setYesNoBooleanChoise(1);
                if (viewCLI.isBooleanChose()){
                    
                    System.out.println("SELECT FROM ONE OF THE PLAYER YOU DAMAGED IN THIS SHOOT");
                    PrintPlayer.print(viewCLI.gameModel.getPlayerDamaged());
                    viewCLI.setTarget1(viewCLI.getUserInput(-1,viewCLI.gameModel.getPlayerDamaged().size()));
                }
            }
    
            viewCLI.notifyController();
        }  else {
        
            PrintNotActualMenu.printMenu(viewCLI.gameModel,viewCLI);
        }
    }
    
    public void payExtraEffect(GameModel gameModel) throws RemoteException {
    
        if (viewCLI.checkCurrent()) {
            int i3;
            int i4;
    
            System.out.println();
            System.out.println("Do you want to use your power up to pay this effect?");
            viewCLI.setYesNoBooleanChoise(2);
            if (viewCLI.isBooleanChose2()) {
        
                System.out.println();
                System.out.println("Select the power up you want to use to pay");
                System.out.println();
                PrintPowerUp.print(gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps(), false);
                System.out.println("How many power ups do you want to use?");
                int number = viewCLI.getUserInput(-1, gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps().size());
        
                switch (number) {
                    case 1:
                        System.out.println("First:");
                        viewCLI.setIndex2(viewCLI.getUserInput(-1, gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps().size()));
                        break;
                    case 2:
                        System.out.println("First:");
                        viewCLI.setIndex2(viewCLI.getUserInput(-1, gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps().size()));
                        System.out.println("Second:");
                        do {
                    
                            i3 = viewCLI.getUserInput(-1, gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps().size());
                        } while (i3 != viewCLI.getIndex2());
                        viewCLI.setIndex3(i3);
                        break;
                    case 3:
                        System.out.println("First:");
                        viewCLI.setIndex2(viewCLI.getUserInput(-1, gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps().size()));
                        System.out.println("Second:");
                        do {
                    
                            i3 = viewCLI.getUserInput(-1, gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps().size());
                        } while (i3 != viewCLI.getIndex2());
                        viewCLI.setIndex3(i3);
                        System.out.println("Third:");
                        do {
                    
                            i4 = viewCLI.getUserInput(-1, gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps().size());
                        } while (i4 != viewCLI.getIndex2() && i4 != viewCLI.getIndex3());
                        viewCLI.setIndex4(i3);
                        break;
                }
            }
        } else {
    
            PrintNotActualMenu.printMenu(viewCLI.gameModel,viewCLI);
        }
    }
    
    public void viewPayEffect() throws RemoteException {
    
        if(viewCLI.checkCurrent()) {
            
            GameModel gameModel = viewCLI.gameModel;
           
            ArrayList<EnumColorCardAndAmmo> cost = new ArrayList<>();
    
            switch (viewCLI.gameModel.getWeaponState()){
                case LockRifle:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()) {
                        //I effect
                        case BaseEffect:
        
                            viewCLI.notifyController();
        
                            break;
                        //II effect
                        case SecondLockEffect:
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Second Lock Effect");
                            cost.add(EnumColorCardAndAmmo.RED);
                            PrintAmmo.print(cost,2);
                            System.out.println();
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                    
                case Electroscythe:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
    
                            viewCLI.notifyController();
                            
                            break;
                        //II effect
                        case ReaperMode :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Reaper Mode");
                            cost.add(EnumColorCardAndAmmo.BLU);
                            cost.add(EnumColorCardAndAmmo.RED);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                //TODO
                case MachineGun:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            PrintEffectWeapon.printMachineGunBasicEffect(gameModel);
                            
                            
                            break;
                        //II effect
                        case FocusShotEffect :
                            
                            break;
                        //III effect
                        case TurretTripodEffect :
                            
                            break;
                        
                    }
                    break;
                
                case TractorBeam:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
    
                            viewCLI.notifyController();
                            
                            break;
                        //II effect
                        case PunisherMode :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Punisher Mode");
                            cost.add(EnumColorCardAndAmmo.RED);
                            cost.add(EnumColorCardAndAmmo.YELLOW);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case Thor:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
    
                        //I effect
                        case BaseEffect :
                            
                            viewCLI.notifyController();
                            
                            break;
                        //II effect
                        case ChainReactionEffect :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Chain Reaction Effect");
                            cost.add(EnumColorCardAndAmmo.BLU);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
    
                        //III effect
                        case HighVoltageEffect :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("High Voltage Effect");
                            cost.add(EnumColorCardAndAmmo.BLU);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case VortexCannon:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            viewCLI.notifyController();
                            
                            break;
                        //II effect
                        case BlackHoleEffect :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Black Hole Effect");
                            cost.add(EnumColorCardAndAmmo.RED);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case Furnace:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case CozyFireMode :
    
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case PlasmaGun:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffectPlusChargedShotEffect :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Charged Shot Effect");
                            cost.add(EnumColorCardAndAmmo.BLU);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case PhaseGlideEffect :
    
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case Heatseeker:
                    
                    viewCLI.notifyController();
                    break;
    
                case Whisper:
                    
                    viewCLI.notifyController();
                    break;
                
                case Hellion:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case NanoTracerMode :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Nano-Tracer Mode");
                            cost.add(EnumColorCardAndAmmo.RED);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case Flamethrower:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case BarbecueMode :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Barbecue Mode");
                            cost.add(EnumColorCardAndAmmo.YELLOW);
                            cost.add(EnumColorCardAndAmmo.YELLOW);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case Zx2:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case ScannerMode :
                            
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case GrenadeLauncher:
    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
    
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case ExtraGrenadeEffect :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Extra Grenade Effect");
                            cost.add(EnumColorCardAndAmmo.RED);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                        
                        case MoveTarget:
                            
                            viewCLI.notifyController();
                            break;
                        
                    }
                    break;
                
                case Shotgun:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case LongBarrelMode :
                            
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case RocketLauncher:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        
                        //I effect
                        case BaseEffectPlusFragmentingWarheadEffect :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Fragmenting Warhead Effect");
                            cost.add(EnumColorCardAndAmmo.YELLOW);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                        
                        //III effect
                        case RocketJumpEffect :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Rocket Jump Effect");
                            cost.add(EnumColorCardAndAmmo.BLU);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case PowerGlove:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case RocketFistMode :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Rocket Fist Mode");
                            cost.add(EnumColorCardAndAmmo.BLU);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case Railgun:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case PiercingMode :
                            
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case Shockwave:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case TsunamiMode :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Tsunami Mode");
                            cost.add(EnumColorCardAndAmmo.YELLOW);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case Cyberblade:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case ShadowstepEffect :
                            
                            viewCLI.notifyController();
                            break;
    
                        //III effect
                        case SliceAndDiceEffect :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Slice and Dice Effect");
                            cost.add(EnumColorCardAndAmmo.YELLOW);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
                
                case Sledgehammer:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewCLI.notifyController();
                            break;
    
                        //II effect
                        case PulverizeMode :
    
                            PrintAmmo.print(gameModel.getActualPlayer().getPlayerBoard().getAmmo(),1);
                            System.out.println("Pulverize Mode");
                            cost.add(EnumColorCardAndAmmo.RED);
                            PrintAmmo.print(cost,2);
                            payExtraEffect(gameModel);
                            viewCLI.notifyController();
                            break;
                    }
                    break;
            }
            viewCLI.notifyController();
        } else {
    
            PrintNotActualMenu.printMenu(viewCLI.gameModel,viewCLI);
        }
        
    }
    //WEAPON
    
    public void viewSelectShootInuput() throws RemoteException {
    
        if (viewCLI.checkCurrent()){
    
    
            System.out.print("MAP: ");
            viewCLI.printMap();
            
            GameModel gameModel = viewCLI.gameModel;
            
            switch (viewCLI.gameModel.getWeaponState()){
                case LockRifle:
                    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            viewLockRifleBasicEffect(gameModel);
                            
                            
                            break;
                        //II effect
                        case SecondLockEffect :
                            
                            viewLockRifleSecondLock(gameModel);
                            
                            break;
                        
                    }
                    break;
                
                case Electroscythe:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewElectroscytheBasicMode();
                            
                            break;
                        //II effect
                        case ReaperMode :
                            
                            viewElectroscytheReaperMode();
                            
                            break;
                        
                    }
                    break;
                
                //TODO
                case MachineGun:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            PrintEffectWeapon.printMachineGunBasicEffect(gameModel);
                            
                            
                            break;
                        //II effect
                        case FocusShotEffect :
                            
                            break;
                        //III effect
                        case TurretTripodEffect :
                            
                            break;
                        
                    }
                    break;
                
                case TractorBeam:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewTractorBeamBasicMode(gameModel);
                            
                            break;
                        //II effect
                        case PunisherMode :
                            
                            viewTractorBeamPunisherMode(gameModel);
                            
                            break;
                        
                    }
                    break;
                
                case Thor:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            viewThorBasicEffect(gameModel);
                            
                            break;
                        //II effect
                        case ChainReactionEffect :
                            
                            viewThorChainReaction(gameModel);
                            
                            break;
                        //III effect
                        case HighVoltageEffect :
                            
                            viewThorHighVoltage(gameModel);
                            
                            break;
                        
                    }
                    break;
                
                case VortexCannon:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            viewVortexCannonBasicEffect(gameModel);
                            
                            break;
                        //II effect
                        case BlackHoleEffect :
                            
                            viewCannonVortexBlackHole(gameModel);
                            
                            break;
                        
                    }
                    break;
                
                case Furnace:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewFurnaceBasicMode(gameModel);
                            
                            break;
                        //II effect
                        case CozyFireMode :
                            
                            viewFurnaceCozyFireMode(gameModel);
        
                            break;
                        
                    }
                    break;
                
                case PlasmaGun:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffectPlusChargedShotEffect :
                            
                            viewPlasmaGunBasicEffectPlusChergedShoot(gameModel);
            
                            break;
                        //II effect
                        case PhaseGlideEffect :
                           
                           viewPlasmaGunPhaseGlide(gameModel);
                            
                            break;
                        //III effect
                        
                        
                    }
                    break;
                
                case Heatseeker:
                    
                    viewHeatseekerEffect(gameModel);
        
                    break;
                
                
                case Whisper:
                    
                    viewWhisperEffect(gameModel);
                    
                    break;
                
                case Hellion:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                           
                           viewHellionBasicMode(gameModel);
                            
                            break;
                        //II effect
                        case NanoTracerMode :
        
                            viewHellionNanoTracerMode(gameModel);
                            
                            break;
                        
                    }
                    break;
                
                case Flamethrower:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewFlamethrowerBasicMode(gameModel);
                            
                            break;
                        //II effect
                        case BarbecueMode :
                            
                            viewFlamethrowerBarbecueMode(gameModel);
                            
                            break;
                        
                    }
                    break;
                
                case Zx2:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewZX2BasicMode(gameModel);
                            
                            break;
                        //II effect
                        case ScannerMode :
                            
                            viewZX2ScannerMode(gameModel);
                            
                            break;
                        
                    }
                    break;
                
                case GrenadeLauncher:
    
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                           viewGrenadeLauncherBasicEffect(gameModel);
                            
                            break;
                        //II effect
                        case ExtraGrenadeEffect :
        
                            viewGrenadeLauncherExtraGrenade(gameModel);
                            break;
                            
                    }
                    break;
                
                case Shotgun:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewShotGunBasicMode(gameModel);
                            
                            break;
                        //II effect
                        case LongBarrelMode :
        
                            viewShotgunLongBarrelMode(gameModel);
                            
                            break;
                    }
                    break;
                
                case RocketLauncher:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        
                        //I effect
                        case BaseEffectPlusFragmentingWarheadEffect :
        
                            viewRocketLauncherBaseEffectPlusFragmentingWarheadEffect(gameModel);
        
                            break;
                            
                        //III effect
                        case RocketJumpEffect :
                            
                            viewRocketLauncherRocketJump(gameModel);
                            
                            break;
                    }
                    break;
                
                case PowerGlove:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewPowerGloveBasicMode(gameModel);
                            
                            break;
                        //II effect
                        case RocketFistMode :
                            
                            viewPowerGloveRocketFirstMode(gameModel);
                            
                            break;
                        
                    }
                    break;
                
                case Railgun:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewRailgunBasicMode(gameModel);
                            
                            break;
                        //II effect
                        case PiercingMode :
                            
                            viewRailgunPiercingMode(gameModel);
                            
                            break;
                        
                    }
                    break;
                
                case Shockwave:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewShockwaveBasicMode(gameModel);
                            
                            break;
                        //II effect
                        case TsunamiMode :
        
                            viewShockwaveTsunamiMode();
                            
                            break;
                    }
                    break;
                
                case Cyberblade:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            viewCyberbladeBasicEffect(gameModel);
                            
                            break;
                        //II effect
                        case ShadowstepEffect :
        
                            viewCyberbladeShadowstep(gameModel);
                            break;
                        //III effect
                        case SliceAndDiceEffect :
        
                            viewCyberbladeSliceAndDice(gameModel);
                            break;
                    }
                    break;
                
                case Sledgehammer:
        
                    switch(viewCLI.gameModel.getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            viewSledgehammerBasicMode(gameModel);
                            
                            break;
                        //II effect
                        case PulverizeMode :
        
                            viewSledgehammerPulverizeMode(gameModel);
                            break;
                    }
                    break;
            }
            viewCLI.notifyController();
            
        } else {
    
            PrintNotActualMenu.printMenu(viewCLI.gameModel,viewCLI);
        }
        
    }
    //WEAPON
    
    //LOCK RIFLE
    public void viewLockRifleBasicEffect(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printLockRifleBasicEffect(gameModel);
        
        //get the player target 1
        viewCLI.setTarget1(viewCLI.getPlayerInput());
    
        viewCLI.notifyController();
    }
    
    public void viewLockRifleSecondLock(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printLockRifleSecondLock(gameModel);
        
        //get the player target 2
        viewCLI.setTarget2(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //ELECTROSCYTHE
    public void viewElectroscytheBasicMode() throws RemoteException{
        
        PrintEffectWeapon.printElectroscytheBasicMode();
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewElectroscytheReaperMode() throws RemoteException{
        
        PrintEffectWeapon.printElectroscytheReaperMode();
    
        viewCLI.notifyController();
    }
    
    //MACHINE GUN
    public void viewMachineGunBasicEffect(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printMachineGunBasicEffect(gameModel);
        //get the player target 1
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //get the player target 2
        viewCLI.setTarget2(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewMachineGunFocusShot(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printMachineGunFocusShot(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.print();
    
        viewCLI.setChoicePlayer(input.nextInt());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewMachineGunTurretTripod(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printMachineGunTurretTripod(gameModel);
        //get the player target 3
        viewCLI.setTarget3(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //TRACTOR BEAM
    public void viewTractorBeamBasicMode(GameModel gameModel) throws RemoteException{
        
        
        PrintEffectWeapon.printTractorBeamBasicMode(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //get the square target
        viewCLI.setSquareInput(1);
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewTractorBeamPunisherMode(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printTractorBeamPunisherMode(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //THOR
    public void viewThorBasicEffect(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printThorBasicEffect(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewThorChainReaction(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printThorChainReaction(gameModel);
        //get the player target
        viewCLI.setTarget2(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewThorHighVoltage(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printThorHighVoltage(gameModel);
        //get the player target
        viewCLI.setTarget3(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //PLASMA GUN
    public void viewPlasmaGunBasicEffectPlusChergedShoot (GameModel gameModel) throws RemoteException{
    
        PrintEffectWeapon.printPlasmaGunBasicEffect(gameModel);
        PrintEffectWeapon.printPlasmaGunChargedShot();
        viewCLI.setYesNoBooleanChoise(1);
        //get the player target
        System.out.println("Target: ");
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewPlasmaGunPhaseGlide(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printPlasmaGunPhaseGlide(gameModel);
        //get the square target
        viewCLI.setSquareInput(1);
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    
    
    //WHISPER
    public void viewWhisperEffect(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printWhisperEffect(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //VORTEX CANNON
    public void viewVortexCannonBasicEffect (GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printCannonVortexBasicEffect(gameModel);
        //get the square target
        viewCLI.setSquareInput(1);
        //get the player target 1
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewCannonVortexBlackHole(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printCannonVortexBlackHole(gameModel);
        //get the player target 2
        viewCLI.setTarget2(viewCLI.getPlayerInput());
        System.out.println("Do you want to choose another target?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()){
        
            System.out.println("Target 2: ");
            viewCLI.setTarget3(viewCLI.getPlayerInput());
        } else {
        
            viewCLI.setTarget3(-1);
        }
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //FURNACE
    public void viewFurnaceBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printFurnaceBasicMode(gameModel);
        
        PrintTarget.printRoom();
        //get the color target
        viewCLI.setColorRoom(viewCLI.setRoomColorInput(gameModel));
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewFurnaceCozyFireMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printFurnaceCozyFireMode(gameModel);
        //get the square target
        viewCLI.setSquareInput(1);
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //HEATSEEKER
    public void viewHeatseekerEffect(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printHeatseekerEffect(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //HELLION
    public void viewHellionBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printHellionBasicMode(gameModel);
        //get the player target 1
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewHellionNanoTracerMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printHellionNanoTracerMode(gameModel);
        //get the player target 1
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //ZX-2
    public void viewZX2BasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printZX2BasicMode(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewZX2ScannerMode(GameModel gameModel) throws RemoteException {
    
        PrintEffectWeapon.printZX2ScannerMode(gameModel);
        System.out.println("Target 1: ");
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        System.out.println("Do you want to choose another target?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()){
        
            System.out.println("Target 2: ");
            viewCLI.setTarget2(viewCLI.getPlayerInput());
        } else {
        
            viewCLI.setTarget2(-1);
        }
        System.out.println("Do you want to choose another target?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()){
        
            System.out.println("Target 3: ");
            viewCLI.setTarget3(viewCLI.getPlayerInput());
        } else {
        
            viewCLI.setTarget3(-1);
        }
        viewCLI.notifyController();
    }
    
    
    
    //GRENADE LAUNCHER
    public void viewGrenadeLauncherBasicEffect(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printGrenadeLauncherBasicEffect(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        PrintTarget.printYesNo();
        //get the square target
        viewCLI.setSquareInput(1);
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewGrenadeLauncherExtraGrenade(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printGrenadeLauncherExtraGrenade(gameModel);
        //get the square target ant put in row 2 and column 2
        viewCLI.setSquareInput(2);
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //SHOTGUN
    public void viewShotGunBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printShotGunBasicMode(gameModel);
        System.out.println("Target 1: ");
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        System.out.println("Do you want to move the target in another square?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()){
            System.out.println("Destination Square: ");
            viewCLI.setSquareInput(1);
        
        } else {
        
            viewCLI.setRow(-1);
            viewCLI.setColumn(-1);
        }
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewShotgunLongBarrelMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printShotGunLongBarrelMode(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //CYBERBLADE
    public void viewCyberbladeBasicEffect(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printCyberbladeBasicEffect(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewCyberbladeShadowstep(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printCyberbladeShadowstep(gameModel);
        //get the square target
        viewCLI.setSquareInput(1);
        //notify controller with new input
        viewCLI.notifyController();
        
    }
    
    public void viewCyberbladeSliceAndDice(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printCyberbladeSliceAndDice(gameModel);
        //get the player target
        viewCLI.setTarget2(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //SLEDGEHAMMER
    public void viewSledgehammerBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printSledgehammerBasicMode(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewSledgehammerPulverizeMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printSledgehammerPulverizeMode(gameModel);
        //get the player target
        viewCLI.setTarget2(viewCLI.getPlayerInput());
        //get the square target
        viewCLI.setSquareInput(1);
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //ROCKET LAUNCHER
    public void viewRocketLauncherBaseEffectPlusFragmentingWarheadEffect (GameModel gameModel) throws RemoteException {
    
        PrintEffectWeapon.printRocketLauncherBasicEffect(gameModel);
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        System.out.println("Do you want to move the target in another square?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()){
            System.out.println("Destination Square: ");
            viewCLI.setSquareInput(1);
        
        } else {
        
            viewCLI.setRow(-1);
            viewCLI.setColumn(-1);
        }
        System.out.println("Do you want to use also the fragmenting warhead effect?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()) {
            PrintEffectWeapon.printRocketLauncherFragmentingWarhead();
        }
    
        viewCLI.notifyController();
    }
    
    public void viewRocketLauncherRocketJump(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printRocketLauncherRocketJump();
        viewCLI.setSquareInput(1);
    
        viewCLI.notifyController();
    }
    
    
    //POWER GLOVE
    public void viewPowerGloveBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printPowerGloveBasicMode(gameModel);
        //get the square target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI. notifyController();
    }
    
    public void viewPowerGloveRocketFirstMode(GameModel gameModel) throws RemoteException {
    
    
        PrintEffectWeapon.printPowerGloveRocketFirstMode(gameModel);
        System.out.println("Target 1: ");
        viewCLI.setTarget2(viewCLI.getPlayerInput());
        System.out.println("Do you want to choose another target?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()){
        
            System.out.println("Target 2: ");
            viewCLI.setTarget3(viewCLI.getPlayerInput());
        } else {
        
            viewCLI.setTarget3(-1);
        }
        viewCLI.notifyController();
        
    }
    
    //SHOCKWAVE
    public void viewShockwaveBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printShockwaveBasicMode(gameModel);
        System.out.println("Target 1: ");
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        System.out.println("Do you want to choose another target?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()){
        
            System.out.println("Target 2: ");
            viewCLI.setTarget2(viewCLI.getPlayerInput());
        } else {
        
            viewCLI.setTarget2(-1);
        }
        System.out.println("Do you want to choose another target?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()){
        
            System.out.println("Target 3: ");
            viewCLI.setTarget3(viewCLI.getPlayerInput());
        } else {
        
            viewCLI.setTarget3(-1);
        }
        viewCLI.notifyController();
    }
    
    public void viewShockwaveTsunamiMode() throws RemoteException{
        
        PrintEffectWeapon.printShockwaveTsunamiMode();
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //RAILGUN
    public void viewRailgunBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printRailgunBasicMode(gameModel);
    
        PrintTarget.printCardinalDirection();
    
        switch (viewCLI.getUserInput(-1,4)){
            case 0:
                viewCLI.setCardinalDirection(EnumCardinalDirection.N);
                break;
            case 1:
                viewCLI.setCardinalDirection(EnumCardinalDirection.S);
                break;
            case 2:
                viewCLI.setCardinalDirection(EnumCardinalDirection.E);
                break;
            case 3:
                viewCLI.setCardinalDirection(EnumCardinalDirection.W);
                break;
        }
    
        System.out.println("Target: ");
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        
        
        
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewRailgunPiercingMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printRailgunPiercingMode(gameModel);
    
        PrintTarget.printCardinalDirection();
    
        switch (viewCLI.getUserInput(-1,4)){
            case 0:
                viewCLI.setCardinalDirection(EnumCardinalDirection.N);
                break;
            case 1:
                viewCLI.setCardinalDirection(EnumCardinalDirection.S);
                break;
            case 2:
                viewCLI.setCardinalDirection(EnumCardinalDirection.E);
                break;
            case 3:
                viewCLI.setCardinalDirection(EnumCardinalDirection.W);
                break;
        }
        
        System.out.println("Target 1: ");
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        System.out.println("Do you want to choose another target?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()){
        
            System.out.println("Target 2: ");
            viewCLI.setTarget2(viewCLI.getPlayerInput());
        } else {
        
            viewCLI.setTarget2(-1);
        }
        //notify controller with new input
        viewCLI.notifyController();
        
    }
    
    //FLAMETHROWER
    public void viewFlamethrowerBasicMode(GameModel gameModel) throws RemoteException {
    
        PrintEffectWeapon.printFlamethrowerBasicMode(gameModel);
        System.out.println("Target 1: ");
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        System.out.println("Do you want to choose another target?");
        viewCLI.setYesNoBooleanChoise(1);
        if(viewCLI.isBooleanChose()){
        
            System.out.println("Target 2: ");
            viewCLI.setTarget2(viewCLI.getPlayerInput());
        } else {
        
            viewCLI.setTarget2(-1);
        }
        viewCLI.notifyController();
    }
    
    
    public void viewFlamethrowerBarbecueMode(GameModel gameModel) throws RemoteException {
    
        PrintEffectWeapon.printFlamethrowerBarbecueMode(gameModel);
        System.out.println("Square 1: ");
        viewCLI.setSquareInput(1);
        System.out.println("Square 2: ");
        viewCLI.setSquareInput(2);
        viewCLI.notifyController();
    }
    
    //FINE ARMI
    //-------------------------------------------------------------------------------------------------
}
