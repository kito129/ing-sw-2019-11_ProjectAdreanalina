package it.polimi.view.cli;

import it.polimi.model.GameModel;
import it.polimi.model.WeaponCard;
import it.polimi.model.WeaponsEffect;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewWeapon {
    
    GameModel gameModel;
    ViewCLI viewCLI;
    
    public void ViewWeapon(ViewCLI viewCLI){
        
        this.gameModel = viewCLI.gameModel;
        this.viewCLI = viewCLI;
        
    }
    
    //weapon method
    
    public void viewSelectWeapon() throws RemoteException {
        
        ArrayList<WeaponCard> weapons = gameModel.getActualPlayer().getPlayerBoard().getPlayerWeapons();
        PrintEffectWeapon.printSelectWeapon();
        PrintWeapon.printName(weapons);
        
        viewCLI.setIndex(viewCLI.getUserInput(0,weapons.size()));
        viewCLI.notifyController();
        
    }
    
    public void viewSelectWeaponEffect() throws RemoteException {
        
        
        ArrayList<WeaponsEffect> weaponEffects = gameModel.getAvailableEffect();
        
        PrintEffectWeapon.printSelectWeaponEffect();
        PrintWeapon.printEffectName(weaponEffects);
    
        viewCLI.setIndex2(viewCLI.getUserInput(0,weaponEffects.size()));
        viewCLI.notifyController();
        
        
    }
    
    public void viewShoot() throws RemoteException {
    
        viewCLI.printMessageAll();
    
        viewCLI.printMap();
    
        viewCLI.notifyController();
        
        
    }
    
    public void viewSelectShootInuput() throws RemoteException {
        
        switch (gameModel.getWeaponState()){
            case LockRifle:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseEffect :
                        
                        break;
                    //II effect
                    case SecondLockEffect :
                        
                        break;
                    
                }
                break;
            
            case Electroscythe:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case ReaperMode :
                        
                        break;
                    
                }
                break;
            
            case MachineGun:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseEffect :
                        
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
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case PunisherMode :
                        
                        break;
                    
                }
                break;
            
            case Thor:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseEffect :
                        
                        break;
                    //II effect
                    case ChainReactionEffect :
                        
                        break;
                    //III effect
                    case HighVoltageEffect :
                        
                        break;
                    
                }
                break;
            
            case VortexCannon:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseEffect :
                        
                        break;
                    //II effect
                    case BlackHoleEffect :
                        
                        break;
                    
                }
                break;
            
            case Furnace:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case CozyFireMode :
                        
                        break;
                    
                }
                break;
            
            case PlasmaGun:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseEffect :
                        
                        break;
                    //II effect
                    case PhaseGlideEffect :
                        
                        break;
                    //III effect
                    case BaseEffectPlusChargedShotEffect :
                        
                        break;
                    
                }
                break;
            
            case Heatseeker:
                
                //only I effect
                
                break;
            
            
            case Whisper:
                
                //only I effect
                
                break;
            
            case Hellion:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case NanoTracerMode :
                        
                        break;
                    
                }
                break;
            
            case Flamethrower:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case BarbecueMode :
                        
                        break;
                    
                }
                break;
            
            case Zx2:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case ScannerMode :
                        
                        break;
                    
                }
                break;
            
            case GrenadeLauncher:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseEffect :
                        
                        break;
                    //II effect
                    case ExtraGrenadeEffect :
                        
                        break;
                    
                }
                break;
            
            case Shotgun:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case LongBarrelMode :
                        
                        break;
                }
                break;
            
            case RocketLauncher:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseEffect :
                        
                        break;
                    //II effect
                    case BaseEffectPlusFragmentingWarheadEffect :
                        
                        break;
                    //III effect
                    case RocketJumpEffect :
                        
                        break;
                }
                break;
            
            case PowerGlove:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case RocketFistMode :
                        
                        break;
                    
                }
                break;
            
            case Railgun:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case PiercingMode :
                        
                        break;
                    
                }
                break;
            
            case Shockwave:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case TsunamiMode :
                        
                        break;
                }
                break;
            
            case Cyberblade:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseEffect :
                        
                        break;
                    //II effect
                    case ShadowstepEffect :
                        
                        break;
                    //III effect
                    case SliceAndDiceEffect :
                        
                        break;
                }
                break;
            
            case Sledgehammer:
                
                switch(gameModel.getWeaponsEffect()){
                    //I effect
                    case BaseMode :
                        
                        break;
                    //II effect
                    case PulverizeMode :
                        
                        break;
                }
                break;
        }
        viewCLI.notifyController();
        
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
        viewCLI.getSquareInput(1);
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
    
        viewCLI.notifyController();
    }
    
    //PLASMA GUN
    public void viewPlasmaGunBasicEffect(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printPlasmaGunBasicEffect(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        
        PrintEffectWeapon.printPlasmaGunChargedShot();
        
        if (viewCLI.getMoveYesNo()==1) {
    
            viewCLI.setBooleanChose(true);
            
        } else {
            viewCLI.setBooleanChose(false);
        }
        viewCLI.notifyController();
    }
    
    public void viewPlasmaGunPhaseGlide(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printPlasmaGunPhaseGlide(gameModel);
        //get the square target
        viewCLI.getSquareInput(1);
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    
    public void viewPlasmaGunChargedShot() throws RemoteException{
        
        PrintEffectWeapon.printPlasmaGunChargedShot();
        //notify controller with new input
        //TODO VEDERE SE é NECESsario notficare anche in caso non ci fossero input da parte della view
        viewCLI. notifyController();
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
        viewCLI.getSquareInput(1);
        //get the player target 1
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewCannonVortexBlackHole(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printCannonVortexBlackHole(gameModel);
        //get the player target 2
        viewCLI.setTarget2(viewCLI.getPlayerInput());
        //get the player target 3
        viewCLI.setTarget3(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //FURNACE
    public void viewFurnaceBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printFurnaceBasicMode(gameModel);
        
        PrintTarget.printRoom();
        //get the color target
        viewCLI.setColorRoom(viewCLI.getRoomColor(gameModel));
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewFurnaceCozyFireMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printFurnaceCozyFireMode(gameModel);
        //get the square target
        viewCLI.getSquareInput(1);
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
        viewCLI.setIndex(viewCLI.getUserInput(0,3));
        //TODO NON VA BENE
        /*
        for(int i = 0; i < input.nextInt(); i++){
    
            //get the player target
            if(i==0){

                setTarget2(getPlayerInput());
            }

            if(i==1){

                setTarget3(getPlayerInput());
            }

            if(i==2){

                setTarget4(getPlayerInput());
            }
        }
        //notify controller with new input
        notifyController();
        
         */
    }
    
    
    
    //GRENADE LAUNCHER
    public void viewGrenadeLauncherBasicEffect(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printGrenadeLauncherBasicEffect(gameModel);
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        
        PrintTarget.printYesNo();
        //get the square target
        viewCLI.getSquareInput(1);
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewGrenadeLauncherExtraGrenade(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printGrenadeLauncherExtraGrenade(gameModel);
        //get the square target ant put in row 2 and column 2
        viewCLI.getSquareInput(2);
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //SHOTGUN
    public void viewShotGunBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printShotGunBasicMode(gameModel);
        //get the square target
        viewCLI.getSquareInput(1);
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
        viewCLI.getSquareInput(1);
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
        viewCLI.getSquareInput(1);
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //ROCKET LAUNCHER
    public void viewRocketLauncherBasicEffect(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printRocketLauncherBasicEffect(gameModel);
        
        PrintTarget.print();
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        
        PrintTarget.printYesNo();
        int i = viewCLI.getUserInput(0,1);
        
        if(i==0){
            
            //get the square target
            viewCLI.getSquareInput(1);
        }
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewRocketLauncherRocketJump(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printRocketLauncherRocketJump(gameModel);
        viewCLI.getSquareInput(1);
    
        viewCLI.notifyController();
    }
    
    public void viewRocketLauncherFragmentingWarhead() throws RemoteException{
        
        PrintEffectWeapon.printRocketLauncherFragmentingWarhead(gameModel);
        //notify controller with new input
        //TODO vedere se è necessario
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
        viewCLI.getSquareInput(1);
        
        PrintTarget.printYesNo();
        int i = viewCLI.getUserInput(0,1);
        
        if(i==0){
            //get the player target 2
            viewCLI.setTarget2(viewCLI.getPlayerInput());
        }
        
        PrintEffectWeapon.printPowerGloveRocketFirstMode2(gameModel);
        
        PrintTarget.printYesNo();
        
        int j= viewCLI.getUserInput(0,1);
        if(j==0){
    
            viewCLI.getSquareInput(2);
            
            PrintTarget.printYesNo();
            
            int h = viewCLI.getUserInput(0,1);
            if(h==0){
                
                //get the player target 3
                viewCLI.setTarget3(viewCLI.getPlayerInput());
            }
        }
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //SHOCKWAVE
    public void viewShockwaveBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printShockwaveBasicMode(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.printNumbTarget();
        do {
            
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            viewCLI.setIndex(input.nextInt());
            
        } while (input.nextInt()<0 || input.nextInt()>3 || input.nextInt()>gameModel.getPlayers(true).size());
        
        int j = viewCLI.getUserInput(0,1);
        
        //TODO NON VA BENE
        for(int h = 0; j < j; j++){
            
            PrintTarget.print();
            if(h==0){
    
                viewCLI.setTarget1(viewCLI.getPlayerInput());
            }
            
            if(h==1){
    
                viewCLI.setTarget2(viewCLI.getPlayerInput());
            }
            
            if(h==2){
    
                viewCLI.setTarget3(viewCLI.getPlayerInput());
            }
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
        Scanner input = new Scanner(System.in);
        
        PrintTarget.printCardinalDirection();
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        viewCLI.setCardinalDirection(gameModel.getMap().getCardinalDirection().get(input.nextInt()));
        //get the player target
        viewCLI.setTarget1(viewCLI.getPlayerInput());
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    public void viewRailgunPiercingMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printRailgunPiercingMode(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.printCardinalDirection();
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        viewCLI.setCardinalDirection(gameModel.getMap().getCardinalDirection().get(input.nextInt()));
        
        PrintTarget.printNumbTarget();
        do {
            
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            viewCLI.setIndex(input.nextInt());
            
        } while (input.nextInt()<0 || input.nextInt()>2);
        
        //TODO non va bene
        for(int i = 0; i < input.nextInt(); i++){
            
            PrintTarget.print();
            if(i==0){
    
                viewCLI.setTarget1(viewCLI.getPlayerInput());
            }
            
            if(i==1){
    
                viewCLI.setTarget2(viewCLI.getPlayerInput());
            }
        }
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //FLAMETHROWER
    public void viewFlamethrowerBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printFlamethrowerBasicMode(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.printNumbTarget();
        do {
            
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            viewCLI.setIndex(input.nextInt());
            
        } while (input.nextInt()<0 || input.nextInt()>2);
        
        if(viewCLI.index==1){
            //get the player target 1
            viewCLI.setTarget1(viewCLI.getPlayerInput());
        }
        
        if(viewCLI.index==2){
            //get the player target 1
            viewCLI.setTarget1(viewCLI.getPlayerInput());
            //get the player target 2
            viewCLI.setTarget2(viewCLI.getPlayerInput());
        }
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //TODO NON VA BENE
    public void viewFlamethrowerBarbecueMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printFlamethrowerBarbecueMode(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.printNumbTarget();
        do {
            
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            viewCLI.setIndex(input.nextInt());
            
        } while (input.nextInt()<0 || input.nextInt()>2);
        
        if(viewCLI.index==1){
            //get the player target 3
            viewCLI.setTarget3(viewCLI.getPlayerInput());
        }
        
        if(viewCLI.index==2){
            //get the player target 3
            viewCLI.setTarget3(viewCLI.getPlayerInput());
            //get the player target 4
            viewCLI.setTarget4(viewCLI.getPlayerInput());
        }
        //notify controller with new input
        viewCLI.notifyController();
    }
    
    //FINE ARMI
    //-------------------------------------------------------------------------------------------------
    
}
