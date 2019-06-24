package it.polimi.controller;

import it.polimi.model.*;
import it.polimi.model.Exception.*;
import it.polimi.model.Weapon.*;
import it.polimi.view.RemoteView;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class WeaponController {
    
    private FunctionController functionController;
    private FunctionModel functionModel;
    
    public WeaponController(FunctionController functionController){
        
        this.functionController = functionController;
        this.functionModel = functionController.functionModel;

    }
    
    public void selectWeapon(RemoteView view) throws RemoteException {
        
        int i;
        WeaponCard weapon;
        GameModel gameModel = this.functionModel.getGameModel();
        
        i = view.getIndex();
        if(this.functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerWeapons().get(i)!=null){
            
            weapon = this.functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerWeapons().get(i);
            
            if(weapon.isCharge()){
                
                //weapon is set controller
                this.functionModel.getGameModel().setWeaponSelected(weapon);
                String name = weapon.getNameWeaponCard();
                
                
                //set extra state
                switch (name) {
                    case "CYBERBLADE":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Cyberblade);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "ELECTOSCYTHE":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Electroscythe);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "FLAME THROWER":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Flamethrower);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "FURNACE":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Furnace);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "GRENADE LAUNCHER":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.GrenadeLauncher);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "HEAT SEEKER":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Heatseeker);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "HELLION":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Hellion);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "LOCK RIFLE":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.LockRifle);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "MACHINE GUN":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.MachineGun);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "PLASMA GUN":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.PlasmaGun);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "POWER GLOVE":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.PowerGlove);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "RAILGUN":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Railgun);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "ROCKET LAUNCHER":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.RocketLauncher);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "SHOCKWAVE":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Shockwave);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "SHOTGUN":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Shotgun);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "SLADGEHAMMER":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Sledgehammer);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "T.H.O.R":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Thor);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "TRACTOR BEAM":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.TractorBeam);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "VORTEX CANNON":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.VortexCannon);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "WHISPER":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Whisper);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                    case "ZX-2":
    
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Zx2);
                        //set the effect list
                        functionController.weaponController.setListEffect(weapon);
                        break;
                }
            } else {
                
                //weapon not charge
                functionController.setErrorState("THIS WEAPON IS NOT CHARGE: YOU CAN'T USE IT NOW");
            }
            
            gameModel.setState(State.SELECTEFFECT);
            
        } else {
    
    
            functionController.mapErrorGestor();
        }
        
    }
    public void setListEffect(WeaponCard weapon){
        
        GameModel gameModel = this.functionModel.getGameModel();
        
        switch (gameModel.getWeaponState()){
        
            default:
                if(!weapon.isOptional()){
                    
                    gameModel.setAvailableEffect(weapon.getWeaponEffects());
                } else {
                    
                    //for the moment take the first, for complex weapon, switch with list of avaible effect
                    ArrayList<WeaponsEffect> baseArray = new ArrayList<>();
                    baseArray.add(weapon.getWeaponEffects().get(0));
                    gameModel.setAvailableEffect(baseArray);
                }
                break;
        }
    }
    
    public void selectWeaponEffect(RemoteView view) throws RemoteException {
        
        GameModel gameModel= this.functionModel.getGameModel();
        int i;
        
        i = view.getIndex2();
        if (gameModel.getAvailableEffect().get(i)!=null) {
            
            gameModel.setActualWeaponEffect(gameModel.getWeaponSelected().getWeaponEffects().get(i));
        } else {
            
            functionController.setErrorState("NOT VALID INPUT");
        }
        gameModel.setState(State.SELECTSHOOTINPUT);
    }
    
    public WeaponCard getCorrectWeapon(String weaponName) throws NotValidInput {
        
        for (WeaponCard a: this.functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerWeapons()){
            
            if(a.getNameWeaponCard().equals(weaponName)){
                
                return a;
            }
        }
        throw  new NotValidInput();
    }
    
    public void selectShootInput(RemoteView view) throws RemoteException {
        
        GameModel gameModel = this.functionModel.getGameModel();
        
        try {
            switch (this.functionModel.getGameModel().getWeaponState()){
                
                case LockRifle:
                    
                    LockRifle lockRifle = (LockRifle) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        
                        //nothing to do
                        
                        //I effect
                        case BaseEffect :
                            
                            this.LockRifleweapon(gameModel,lockRifle,view);
                            
                            break;
                        //II effect
                        case SecondLockEffect :
                            
                            this.LockRifleweapon(gameModel,lockRifle,view);
                        
                        
                    }
                    break;
                
                case Electroscythe:
                    
                    Electroscythe electroscythe = (Electroscythe) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        
                        //nothing to do
                        
                        //I effect
                        case BaseMode :
                            
                            this.ElectroscytheWeapon(gameModel,electroscythe,view);
                            
                            break;
                        //II effect
                        case ReaperMode :
                            
                            this.ElectroscytheWeapon(gameModel,electroscythe,view);
                            
                            break;
                        
                    }
                    break;
                
                case MachineGun:
                    
                    MachineGun machineGun = (MachineGun) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            this.MachineGun(gameModel,machineGun,view);
                            
                            break;
                        //II effect
                        case FocusShotEffect :
                            
                            this.MachineGun(gameModel,machineGun,view);
                            
                            break;
                        //III effect
                        case TurretTripodEffect :
                            
                            this.MachineGun(gameModel,machineGun,view);
                            
                            break;
                        
                    }
                    break;
                
                case TractorBeam:
                    
                    TractorBeam tractorBeam = (TractorBeam) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.TractorBeam(gameModel,tractorBeam,view);
                            
                            break;
                        //II effect
                        case PunisherMode :
                            
                            this.TractorBeam(gameModel,tractorBeam,view);
                            
                            break;
                        
                    }
                    break;
                
                case Thor:
                    
                    Thor thor =(Thor) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            this.Thor(gameModel,thor,view);
                            
                            break;
                        //II effect
                        case ChainReactionEffect :
                            
                            this.Thor(gameModel,thor,view);
                            
                            break;
                        //III effect
                        case HighVoltageEffect :
                            
                            this.Thor(gameModel,thor,view);
                            
                            break;
                        
                    }
                    break;
                
                case VortexCannon:
                    
                    VortexCannon vortexCannon = (VortexCannon) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            this.VortexCannon(gameModel,vortexCannon,view);
                            
                            break;
                        //II effect
                        case BlackHoleEffect :
                            
                            this.VortexCannon(gameModel,vortexCannon,view);
                            
                            break;
                        
                    }
                    break;
                
                case Furnace:
                    
                    Furnace furnace = (Furnace) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.Furnace(gameModel,furnace,view);
                            
                            break;
                        //II effect
                        case CozyFireMode :
                            
                            this.Furnace(gameModel,furnace,view);
                            
                            break;
                        
                    }
                    break;
                
                case PlasmaGun:
                    
                    PlasmaGun plasmaGun = (PlasmaGun) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            this.PlasmaGun(gameModel,plasmaGun,view);
                            
                            break;
                        //II effect
                        case PhaseGlideEffect :
                            
                            this.PlasmaGun(gameModel,plasmaGun,view);
                            
                            break;
                        //III effect
                        case BaseEffectPlusChargedShotEffect :
                            
                            this.PlasmaGun(gameModel,plasmaGun,view);
                            
                            break;
                        
                    }
                    break;
                
                case Heatseeker:
                    
                    Heatseeker heatseeker = (Heatseeker) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    
                    this.HeatSeeker(gameModel,heatseeker,view);
                    
                    //only I effect
                    
                    break;
                
                
                case Whisper:
                    
                    Whisper whisper = (Whisper) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    
                    this.Whisper(gameModel,whisper,view);
                    
                    //only I effect
                    
                    break;
                
                case Hellion:
                    
                    Hellion hellion = (Hellion) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.Hellion(gameModel,hellion,view);
                            
                            break;
                        //II effect
                        case NanoTracerMode :
                            
                            this.Hellion(gameModel,hellion,view);
                            
                            break;
                        
                    }
                    break;
                
                case Flamethrower:
                    
                    Flamethrower flamethrower = (Flamethrower) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.Flamethrower(gameModel,flamethrower,view);
                            
                            break;
                        //II effect
                        case BarbecueMode :
                            
                            this.Flamethrower(gameModel,flamethrower,view);
                            
                            break;
                        
                    }
                    break;
                
                case Zx2:
                    
                    Zx2 zx2 = (Zx2) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.Zx2(gameModel,zx2,view);
                            
                            break;
                        //II effect
                        case ScannerMode :
                            
                            this.Zx2(gameModel,zx2,view);
                            
                            break;
                        
                    }
                    break;
                
                case GrenadeLauncher:
                    
                    GrenadeLauncher grenadeLauncher = (GrenadeLauncher) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            this.GrenadeLauncher(gameModel,grenadeLauncher,view);
                            
                            break;
                        //II effect
                        case ExtraGrenadeEffect :
                            
                            this.GrenadeLauncher(gameModel,grenadeLauncher,view);
                            
                            break;
                        
                        case MoveTarget:
                            
                            //TODO
                            
                            break;
                        
                    }
                    break;
                
                case Shotgun:
                    
                    Shotgun shotgun = (Shotgun) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.Shotgun(gameModel,shotgun,view);
                            
                            break;
                        //II effect
                        case LongBarrelMode :
                            
                            this.Shotgun(gameModel,shotgun,view);
                            
                            break;
                    }
                    break;
                
                case RocketLauncher:
                    
                    RocketLauncher rocketLauncher = (RocketLauncher) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            this.RocketLauncher(gameModel,rocketLauncher,view);
                            
                            break;
                        //II effect
                        case BaseEffectPlusFragmentingWarheadEffect :
                            
                            this.RocketLauncher(gameModel,rocketLauncher,view);
                            
                            break;
                        //III effect
                        case RocketJumpEffect :
                            
                            this.RocketLauncher(gameModel,rocketLauncher,view);
                            
                            break;
                    }
                    break;
                
                case PowerGlove:
                    
                    PowerGlove powerGlove = (PowerGlove) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.PowerGlove(gameModel,powerGlove,view);
                            
                            break;
                        //II effect
                        case RocketFistMode :
                            
                            this.PowerGlove(gameModel,powerGlove,view);
                            
                            break;
                        
                    }
                    break;
                
                case Railgun:
                    
                    Railgun railGun = (Railgun) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.RailGun(gameModel,railGun,view);
                            
                            break;
                        //II effect
                        case PiercingMode :
                            
                            this.RailGun(gameModel,railGun,view);
                            
                            break;
                        
                    }
                    break;
                
                case Shockwave:
                    
                    Shockwave shockwave = (Shockwave) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.Shockwave(gameModel,shockwave,view);
                            
                            break;
                        //II effect
                        case TsunamiMode :
                            
                            this.Shockwave(gameModel,shockwave,view);
                            
                            break;
                    }
                    break;
                
                case Cyberblade:
                    
                    Cyberblade cyberblade = (Cyberblade) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            this.Cyberblade(gameModel,cyberblade,view);
                            
                            break;
                        //II effect
                        case ShadowstepEffect :
                            
                            this.Cyberblade(gameModel,cyberblade,view);
                            
                            break;
                        //III effect
                        case SliceAndDiceEffect :
                            
                            this.Cyberblade(gameModel,cyberblade,view);
                            
                            break;
                    }
                    break;
                
                case Sledgehammer:
                    
                    Sledgehammer sledgehammer = (Sledgehammer) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.Sledgehammer(gameModel,sledgehammer,view);
                            
                            break;
                        //II effect
                        case PulverizeMode :
                            
                            this.Sledgehammer(gameModel,sledgehammer,view);
                            
                            break;
                    }
                    break;
            }
        } catch (NotValidInput notValidInput) {
            notValidInput.printStackTrace();
        }
        this.functionModel.getGameModel().setState(State.SHOOT);
        
    }
    
    
    //WEAPON
    public void LockRifleweapon(GameModel gameModel, LockRifle weapon, RemoteView view) throws RemoteException {
        
        Player currentPlayer = gameModel.getActualPlayer();
        Map map = gameModel.getMap();
        Player targetBase;
        Player targetSecondLock;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffect:
                
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, currentPlayer, targetBase);
                    gameModel.setBeforeEffect(WeaponsEffect.BaseEffect);
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case SecondLockEffect:
                
                if(gameModel.getBeforeEffect()==WeaponsEffect.BaseEffect) {
                    
                    try {
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetSecondLock = gameModel.getPlayerById(view.getTarget2());
                        if (targetBase != targetSecondLock) {
                            
                            weapon.secondLockEffect(map, currentPlayer, targetSecondLock);
                        } else {
                            
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
                        
                        gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS THE SAME OF BASE EFFECT");
                    } catch (NotVisibleTarget notVisibleTarget) {
                        
                        gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                    } catch (MapException e) {
                        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                }else{
                    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
        }
    }
    
    public void ElectroscytheWeapon(GameModel gameModel, Electroscythe weapon, RemoteView view) throws RemoteException{
        
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    
                    weapon.baseMode(map,currentPlayer);
                } catch (NoTargetInSquare noTargetInSquare) {
                    
                    gameModel.setErrorMessage("ERROR: NO TARGET IN YOUR SQUARE");
                } catch (MapException mapException){
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            case ReaperMode:
                
                try {
                    
                    weapon.reaperMode(map,currentPlayer);
                } catch (NoTargetInSquare noTargetInSquare) {
                    
                    gameModel.setErrorMessage("ERROR: NO TARGET IN YOUR SQUARE");
                } catch (MapException mapException ) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    
    public void TractorBeam(GameModel gameModel, TractorBeam weapon, RemoteView view) throws RemoteException {
        
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        Square destSquareBase;
        Player targetBaseOrPunisher;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    
                    destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    targetBaseOrPunisher = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseMode(map, destSquareBase, currentPlayer, targetBaseOrPunisher);
                    gameModel.setBeforeEffect(WeaponsEffect.BaseMode);
                    
                }catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE DESTINATION SQUARE IS NOT VISIBLE FROM YOUR POSITION");
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: YOU CAN'T MOVE YOUR TARGET MORE THAN TWO MOVES");
                } catch (MapException mapException) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case PunisherMode:
                
                try {
                    
                    targetBaseOrPunisher = gameModel.getPlayerById(view.getTarget1());
                    weapon.punisherMode(map, currentPlayer, targetBaseOrPunisher);
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS MORE THAN THO MOVES FROM YOU");
                } catch (MapException mapException) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void Whisper(GameModel gameModel, Whisper weapon, RemoteView view) throws RemoteException {
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffect:
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, currentPlayer, targetBase);
                    gameModel.setBeforeEffect(WeaponsEffect.BaseEffect);
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT AT LEAST 2 MOVES FROM YOU");
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NON VISIBLE");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void VortexCannon (GameModel gameModel, VortexCannon weapon, RemoteView view) throws RemoteException {
        
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Square vortexSquare;
        Player target1BlackHole;
        Player target2BlackHole;
        ArrayList<Player> targetsBlackHole;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffect:
                
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    vortexSquare = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.baseEffect(map, vortexSquare, currentPlayer, targetBase);
                    gameModel.setBeforeEffect(WeaponsEffect.BaseEffect);
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARE IS NOT VISIBLE FROM YOUR POSITION");
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARE IN NOT AT LEAST ONE MOVE FROM YOU OR THE CHOSEN TARGET IS NOT " +
                            "ON VORTEX SQUARE OR IS NOT DISTANCE ONE MOVE FROM THE VORTEX SQUARE");
                } catch (MapException mapException) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case BlackHoleEffect:
                
                if(gameModel.getBeforeEffect()==WeaponsEffect.BaseEffect) {
                    
                    try {
                        
                        vortexSquare = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        target1BlackHole = gameModel.getPlayerById(view.getTarget2());
                        if (view.getTarget3() == -1) {
                            
                            if (target1BlackHole != targetBase) {
                                
                                targetsBlackHole=new ArrayList<>();
                                targetsBlackHole.add(target1BlackHole);
                                weapon.blackHoleEffect(map, vortexSquare, currentPlayer, targetsBlackHole);
                            } else {
                                
                                throw new NotValidInput();
                            }
                        } else {
                            
                            target2BlackHole = gameModel.getPlayerById(view.getTarget3());
                            if ((target1BlackHole != target2BlackHole) && (target1BlackHole != targetBase) && (target2BlackHole != targetBase)) {
                                
                                targetsBlackHole=new ArrayList<>();
                                targetsBlackHole.add(target1BlackHole);
                                targetsBlackHole.add(target2BlackHole);
                                weapon.blackHoleEffect(map, vortexSquare, currentPlayer, targetsBlackHole);
                            } else {
                                
                                throw new NotValidInput();
                            }
                        }
                    } catch (NotValidDistance notValidDistance) {
                        
                        gameModel.setErrorMessage("THE CHOSEN TARGET IS NOT ON VORTEX SQUARE OR IS NOT DISTANCE ONE MOVE FROM THE VORTEX SQUARE");
                    } catch (NotValidInput notValidInput) {
                        
                        gameModel.setErrorMessage("ONE OR TWO BLACK HOLE TARGET ARE THE SAME OF BASE EFFECT OR ARE EQUAL BETWEEN THEM");
                    } catch (MapException e) {
                        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                } else {
                    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
        }
        
    }
    
    public void Furnace(GameModel gameModel, Furnace weapon, RemoteView view) throws RemoteException {
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        EnumColorSquare roomTarget;
        Square targetSquareCozy;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    
                    roomTarget = view.getColorRoom();
                    weapon.baseMode(map, currentPlayer, roomTarget);
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: CHOOSE A DIFFERENT ROOM THAN YOURS ");
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN ROOM IS NOT VISIBLE ");
                } catch (MapException mapException) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case CozyFireMode:
                
                try {
                    
                    targetSquareCozy = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.cozyFireMode(map, currentPlayer, targetSquareCozy);
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARE IS NOT DISTANCE ONE MOVE");
                } catch (MapException mapException) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void HeatSeeker(GameModel gameModel, Heatseeker weapon, RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffect:
                
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.BaseEffect(map, currentPlayer, targetBase);
                } catch (VisibleTarget visibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS VISIBLE");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void Hellion(GameModel gameModel, Hellion weapon, RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBaseOrTracer;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    
                    targetBaseOrTracer = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseMode(map, currentPlayer, targetBaseOrTracer);
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IN NOT AT LEAST ONE MOVE FROM YOU" );
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException mapException) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case NanoTracerMode:
                
                try {
                    
                    targetBaseOrTracer = gameModel.getPlayerById(view.getTarget1());
                    weapon.nanoTracerMode(map, currentPlayer, targetBaseOrTracer);
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IN NOT AT LEAST ONE MOVE FROM YOU" );
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException mapException) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void RailGun(GameModel gameModel, Railgun weapon,RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player target1;
        Player target2;
        EnumCardinalDirection direction;
        ArrayList<Player> piercingTargets = new ArrayList<>();
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    
                    target1 = gameModel.getPlayerById(view.getTarget1());
                    direction = view.getCardinalDirection();
                    weapon.baseMode(map, currentPlayer, target1, direction);
                } catch (NotValidCardinalDirection notValidCardinalDirection) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN DIRECTION DOESN'T EXIST");
                    
                } catch (NotInDirection notInDirection) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN CHOSEN CARDINAL DIRECTION");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case PiercingMode:
                
                try {
                    
                    target1 = gameModel.getPlayerById(view.getTarget1());
                    direction = view.getCardinalDirection();
                    if (view.getTarget2() != -1) {
                        
                        target2 = gameModel.getPlayerById(view.getTarget2());
                        piercingTargets.add(target1);
                        piercingTargets.add(target2);
                        weapon.piercingMode(map, currentPlayer, piercingTargets, direction);
                    } else {
                        
                        piercingTargets.add(target1);
                        weapon.piercingMode(map, currentPlayer, piercingTargets, direction);
                    }
                } catch (NotValidCardinalDirection notValidCardinalDirection) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN DIRECTION DON'T EXIST");
                } catch (NotInDirection notInDirection) {
                    
                    gameModel.setErrorMessage("ERROR: ONE OF CHOSEN TARGET IS NOT IN CHOSEN CARDINAL DIRECTION");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void Zx2(GameModel gameModel, Zx2 weapon,RemoteView view) throws RemoteException {
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player target1;
        Player target2;
        Player target3;
        ArrayList<Player> scannerModeTargets = new ArrayList<>();
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    
                    target1 = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseMode(map, currentPlayer, target1);
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                
                break;
            
            case ScannerMode:
                
                try {
                    
                    target1 = gameModel.getPlayerById(view.getTarget1());
                    if ((view.getTarget2() == -1) && (view.getTarget3() == -1)) {
                        
                        scannerModeTargets.add(target1);
                        weapon.scannerMode(map, currentPlayer, scannerModeTargets);
                    } else if (view.getTarget3() == -1) {
                        
                        target2 = gameModel.getPlayerById(view.getTarget2());
                        if (target1 != target2) {
                            
                            scannerModeTargets.add(target1);
                            scannerModeTargets.add(target2);
                            weapon.scannerMode(map, currentPlayer, scannerModeTargets);
                        } else {
                            
                            throw new NotValidInput();
                        }
                        
                    } else {
                        
                        target2 = gameModel.getPlayerById(view.getTarget2());
                        target3 = gameModel.getPlayerById(view.getTarget3());
                        if ((target1 != target2) && (target1 != target3) && (target2 != target3)) {
                            
                            scannerModeTargets.add(target1);
                            scannerModeTargets.add(target2);
                            scannerModeTargets.add(target3);
                            weapon.scannerMode(map, currentPlayer, scannerModeTargets);
                        } else {
                            
                            throw new NotValidInput();
                            
                        }
                    }
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: ONE OF CHOSEN TARGET IS NOT VISIBLE");
                } catch (NotValidInput notValidInput) {
                    
                    gameModel.setErrorMessage("TWO OR THREE TARGETS ARE EQUAL BETWEEN THEM");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void PlasmaGun (GameModel gameModel, PlasmaGun weapon, RemoteView view) throws RemoteException  {
        
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        Player targetBase;
        Square destSquarePhaseGlide;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffectPlusChargedShotEffect:
                
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, currentPlayer, targetBase);
                    if(view.isBooleanChose()) {
                        
                        weapon.chargedShotEffect(currentPlayer, targetBase);
                    }
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case PhaseGlideEffect:
                
                try {
                    
                    destSquarePhaseGlide = gameModel.getMap().getSquare(view.getRow(),view.getColumn());
                    weapon.phaseGlideEffect(map,destSquarePhaseGlide,currentPlayer);
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: YOU CAN MOVE YOUR TARGET ONLY ONE OR TWO MOVES");
                }
                break;
        }
    }
    
    public void Cyberblade(GameModel gameModel, Cyberblade weapon,RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Square destSquareShadow;
        Player targetSliceAndDice;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffect:
                
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, currentPlayer, targetBase);
                    gameModel.setBeforeEffect(WeaponsEffect.BaseEffect);
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE");
                } catch (MapException mapException) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
            case ShadowstepEffect:
                
                try{
                    
                    destSquareShadow = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.shadowstepEffect(map,currentPlayer,destSquareShadow);
                }catch(NotValidDistance notValidDistance){
                    
                    gameModel.setErrorMessage("ERROR: YOU CAN MOVE YOURSELF ONLY ONE MOVES");
                }catch (MapException mapException){
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
            case SliceAndDiceEffect:
                
                if(gameModel.getBeforeEffect()==WeaponsEffect.BaseEffect) {
                    
                    try {
                        
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetSliceAndDice = gameModel.getPlayerById(view.getTarget2());
                        if (targetBase != targetSliceAndDice) {
                            
                            weapon.sliceAndDiceEffect(map, currentPlayer, targetSliceAndDice);
                        } else {
                            
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
                        
                        gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS THE SAME OF BASE EFFECT");
                    } catch (NotValidDistance notValidDistance) {
                        
                        gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE");
                    } catch (MapException mapException) {
                        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                } else {
                    
                    gameModel.setErrorMessage("ERROR!");
                }
        }
    }
    
    public void GrenadeLauncher(GameModel gameModel, GrenadeLauncher weapon, RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Square destSquareBase;
        Square targetSquareExtra;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffect:
                
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, targetBase, currentPlayer);
                    gameModel.setBeforeEffect(WeaponsEffect.BaseEffect);
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case MoveTarget:
                
                try {
                    
                    destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.moveTarget(map, targetBase, destSquareBase);
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: YOU CAN MOVE YOUR TARGET ONLY ONE MOVES");
                } catch (MapException mapException) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case ExtraGrenadeEffect:
                
                if(gameModel.getBeforeEffect() == WeaponsEffect.BaseEffect) {
                    
                    try {
                        
                        targetSquareExtra = gameModel.getMap().getSquare(view.getRow2(), view.getColumn2());
                        weapon.extraGrenadeEffect(map, currentPlayer, targetSquareExtra);
                    } catch (NotVisibleTarget notVisibleTarget) {
                        
                        gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARE IS NOT VISIBLE");
                    } catch (MapException mapExcpetion) {
                        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    } catch (NoTargetInSquare noTargetInSquare) {
                        
                        gameModel.setErrorMessage("ERROR: NO TARGET IN THE CHOSEN SQUARE");
                    }
                } else {
                    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
        }
    }
    
    public void Thor(GameModel gameModel, Thor weapon, RemoteView view) throws RemoteException {
        
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        Player targetBase;
        Player targetChainReaction;
        Player targetHighVoltage;
        
        switch (gameModel.getActualWeaponEffect()) {
            case BaseEffect:
                
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, currentPlayer, targetBase);
                    gameModel.setBeforeEffect(WeaponsEffect.BaseEffect);
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException e) {
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
            case ChainReactionEffect:
                
                if(gameModel.getBeforeEffect() == WeaponsEffect.BaseEffect) {
                    
                    try {
                        
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetChainReaction = gameModel.getPlayerById(view.getTarget2());
                        if ((map.isVisible(targetBase, targetChainReaction)) && (targetBase != targetChainReaction)) {
                            
                            weapon.chainReactionEffect(currentPlayer, targetChainReaction);
                            gameModel.setBeforeEffect(WeaponsEffect.ChainReactionEffect);
                        } else {
                            
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
                        
                        gameModel.setErrorMessage("ERROR: THE TARGET OF BASE EFFECT DON'T SEE THE CHOSEN TARGET OR THE CHOSEN TARGET IS NOT DIFFERENT " +
                                "FROM THE TARGET OF BASE EFFECT ");
                    } catch (MapException e) {
                        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                } else {
                    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
            
            case HighVoltageEffect:
                
                if(gameModel.getBeforeEffect() == WeaponsEffect.ChainReactionEffect) {
                    
                    try {
                        
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetChainReaction = gameModel.getPlayerById(view.getTarget2());
                        targetHighVoltage = gameModel.getPlayerById(view.getTarget3());
                        if ((map.isVisible(targetChainReaction, targetHighVoltage)) && (targetChainReaction != targetHighVoltage)
                                && (targetBase != targetHighVoltage)) {
                            
                            weapon.highVoltageEffect(currentPlayer, targetHighVoltage);
                        } else {
                            
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
                        
                        gameModel.setErrorMessage("ERROR: THE TARGET OF CHAIN REACTION DON'T SEE THE CHOSEN TARGET OR THE CHOSEN TARGET IS NOT DIFFERENT" +
                                "FROM THE TARGET OF BASE EFFECT AND FROM THE TARGET OF CHAIN REACTION");
                    } catch (MapException e) {
                        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                } else {
                    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
        }
    }
    
    public void Shockwave(GameModel gameModel, Shockwave weapon,RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player target1Base;
        Player target2Base;
        Player target3Base;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    
                    target1Base = gameModel.getPlayerById(view.getTarget1());
                    if ((view.getTarget3() == -1) && (view.getTarget2() == -1)) {
                        
                        weapon.baseMode(map, currentPlayer, target1Base);
                        
                    } else if ((view.getTarget3() == -1) && (view.getTarget2() != -1)) {
                        
                        target2Base = gameModel.getPlayerById(view.getTarget2());
                        weapon.baseMode(map, currentPlayer, target1Base, target2Base);
                    } else {
                        
                        target2Base = gameModel.getPlayerById(view.getTarget2());
                        target3Base = gameModel.getPlayerById(view.getTarget3());
                        weapon.baseMode(map, currentPlayer, target1Base, target2Base, target3Base);
                    }
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET ARE NOT DIFFERENT AMONG THEM OR EACH ONE ARE NOT DISTANCE" +
                            "EXACTLY ONE MOVE");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case TsunamiMode:
                
                try{
                    
                    ArrayList<Player> allPlayerInGame=new ArrayList<>(gameModel.getPlayers(false));
                    weapon.tsunamiMode(map,currentPlayer,allPlayerInGame);
                }catch (NotValidDistance notValidDistance){
                    
                    gameModel.setErrorMessage("ERROR: NO PLAYER AT ONE MOVE FROM YOU");
                }
                break;
        }
    }
    
    
    //TODO andre
    public void MachineGun(GameModel gameModel, MachineGun weapon, RemoteView view) throws RemoteException{
        
        //necessary from model
        Player currentPlayer = gameModel.getActualPlayer();
        Map map = gameModel.getMap();
        //necessary input
        Player target1Base;
        Player target2Base; // se l'utente per l'effetto base non vuole colpire due target, questo viene messo a null.
        Player targetFocusShot;
        Player targetTurretTripod;
        //necessary input
        WeaponsEffect effect = view.getWeaponsEffect();
        
        boolean choice1;
        boolean choice2;
        choice1 = view.isUseSecondEffect();
        choice2 = view.isUseThirdEffect();
        
        //array for target
        ArrayList<Player> targetBase = new ArrayList<>();
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffect:
                //base mode
                try {
                    
                    //get the target
                    target1Base = gameModel.getPlayerById(view.getTarget1());
                    target2Base = gameModel.getPlayerById(view.getTarget2()); //possible null
                    
                    if (target2Base == null) {
                        
                        targetBase.add(target1Base);
                        weapon.baseEffect(map, currentPlayer, targetBase);
                        
                        gameModel.setPlayerDamagedIndex(1);
                        
                    } else if (target1Base != target2Base) {
                        
                        targetBase.add(target1Base);
                        targetBase.add(target2Base);
                        weapon.baseEffect(map, currentPlayer, targetBase);
    
                        gameModel.setPlayerDamagedIndex(2);
    
                    } else {
                        
                        throw new NotValidInput();
                    }
    
                    gameModel.setBeforeEffect(WeaponsEffect.BaseEffect);
                    
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    //gestione target 1 o 2 effetto base non visibili con il curretnPLayer
                } catch (NotValidInput notValidInput) {
                    
                    //gestione target 1==target 2 effetto base
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            
            case FocusShotEffect:
                
                if(gameModel.getBeforeEffect()== WeaponsEffect.BaseEffect) {
                    
                    try {
                        
                        //get the target
                        target1Base = gameModel.getPlayerById(view.getTarget1());
                        target2Base = gameModel.getPlayerById(view.getTarget2()); // possible null
                        targetFocusShot = gameModel.getPlayerById(view.getTarget3());
                        
                        if (targetFocusShot == target1Base) {
                            
                            weapon.focusShotEffect(currentPlayer, targetFocusShot);
                            
                            gameModel.getPlayerDamaged().add(target1Base);
                            
                            if(targetFocusShot == target2Base) {
                                
                                weapon.focusShotEffect(currentPlayer, targetFocusShot);
    
                                gameModel.getPlayerDamaged().add(target2Base);
    
    
                            } else {
                                
                                throw new NotValidInput();
                            }
                            
                        } else {
                            
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
                        
                        //gestione se target focus shot dovesse essere diverso sia da target 1 sia target 2 effetto base
                    } catch (MapException e) {
                        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                } else {
                    
                    gameModel.setErrorMessage("ERROR!");
                }
                
                break;
            
            case TurretTripodEffect:
                
                if(gameModel.getBeforeEffect() == WeaponsEffect.BaseEffect) {
                    
                    if (gameModel.getPlayerDamagedIndex() == 1) {
                        if(choice1) {
                            
                            //get the target
                            try {
                                
                                //TODO andre
                                target1Base = gameModel.getPlayerById(view.getTarget1());
                                target2Base = gameModel.getPlayerById(view.getTarget2());
                                targetFocusShot = gameModel.getPlayerById(view.getTarget3());
                                targetTurretTripod = gameModel.getPlayerById(view.getTarget4());
                                
                            } catch (MapException e) {
                                
                                gameModel.setErrorMessage("ERROR: MAP ERROR");
                            }
                        }
                        
                        
                        
                    } else if (gameModel.getPlayerDamagedIndex() == 2) {
                        
                        //get the target
                        try {
                            //TODO andre
                            target1Base = gameModel.getPlayerById(view.getTarget1());
                            target2Base = gameModel.getPlayerById(view.getTarget2());
                            targetFocusShot = gameModel.getPlayerById(view.getTarget3());
                            targetTurretTripod = gameModel.getPlayerById(view.getTarget4());
                        } catch (MapException e) {
                            
                            gameModel.setErrorMessage("ERROR: MAP ERROR");
                        }
                        
                    }
                } else {
                    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
        }
    }
    
    public void RocketLauncher(GameModel gameModel, RocketLauncher weapon,RemoteView view) throws RemoteException {
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Square destSquareBase;
        Square destSquareJump;
        boolean choice1;
        
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffectPlusFragmentingWarheadEffect:
                
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    choice1 = view.isUseSecondEffect();
                    if(!choice1){
                        
                        if ((view.getRow() != -1) && ((view.getColumn() != -1))) {
                            
                            destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                            weapon.baseEffect(map, targetBase, currentPlayer, destSquareBase);
                        } else {
                            
                            destSquareBase = null;
                            weapon.baseEffect(map, targetBase, currentPlayer, destSquareBase);
                        }
                    }else{
                        
                        if ((view.getRow() != -1) && ((view.getColumn() != -1))) {
                            
                            destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                            weapon.baseEffectWithFragmenting(map, targetBase, currentPlayer, destSquareBase);
                        } else {
                            
                            destSquareBase = null;
                            weapon.baseEffectWithFragmenting(map, targetBase, currentPlayer, destSquareBase);
                        }
                    }
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                    
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS IN YOUR SQUARE OR YOU ARE NOT MOVING ONE MOVE YOUR TARGET ");
                } catch (MapException mapExcpetion) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                } catch (NoTargetInSquare noTargetInSquare){
                    
                    gameModel.setErrorMessage("ERROR: NO TARGET IN CHOSEN SQUARE ");
                }
                
                break;
            
            case RocketJumpEffect:
                
                try{
                    
                    destSquareJump = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.rocketJumpEffect(map,currentPlayer,destSquareJump);
                }catch (NotValidDistance notValidDistance){
                    gameModel.setErrorMessage("ERROR: YOU MOVE ONLY ONE OR TWO MOVES");
                }catch (MapException mapException){
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    
    public void Shotgun(GameModel gameModel, Shotgun weapon,RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Square destSquareBase;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    if ((view.getRow() == -1) && (view.getColumn() == -1)) {
                        
                        destSquareBase = null;
                        weapon.baseMode(map, currentPlayer,targetBase,destSquareBase);
                    }else{
                        
                        destSquareBase = map.getSquare(view.getRow(),view.getColumn());
                        weapon.baseMode(map, currentPlayer,targetBase,destSquareBase);
                    }
                    
                }catch (MapException mapExcpetion){
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }catch (NotValidDistance notValidDistance){
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE OR YOU ARE NOT MOVING " +
                            "ONE MOVE THE TARGET");
                }
                break;
            case LongBarrelMode:
                
                try{
                    
                    targetBase=gameModel.getPlayerById(view.getTarget1());
                    weapon.longBarrelMode(map,currentPlayer,targetBase);
                }catch (MapException mapException){
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }catch (NotValidDistance notValidDistance){
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT EXACTLY AT ONE MOVE FROM YOU");
                }
                break;
        }
    }
    
    public void Flamethrower(GameModel gameModel, Flamethrower weapon,RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Player target2;
        Square targetSquare1;
        Square targetSquare2;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffect:
                
                try{
                    
                    targetBase=gameModel.getPlayerById(view.getTarget1());
                    if(view.getTarget2()==-1){
                        
                        weapon.baseMode(map,currentPlayer,targetBase);
                    }else{
                        
                        target2 = gameModel.getPlayerById(view.getTarget2());
                        weapon.baseMode(map,currentPlayer,targetBase,target2);
                    }
                    
                }catch (MapException mapException){
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }catch (NotValidDistance notValidDistance){
                    
                    gameModel.setErrorMessage("ERROR: THE FIRST CHOSEN TARGET ARE NOT AT ONE MOVE FROM YOU OR" +
                            "THE SECOND CHOSEN TARGET ARE NOT ONE MOVE FROM THE FIRST");
                    
                }catch (NotInSameDirection notInSameDirection){
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGETS ARE NOT IN THE SAME DIRECTION");
                    
                }
                break;
            case BarbecueMode:
                
                try{
                    
                    targetSquare1 = map.getSquare(view.getRow(),view.getColumn());
                    targetSquare2 = map.getSquare(view.getRow2(),view.getColumn2());
                    weapon.barbecueMode(map,currentPlayer,targetSquare1,targetSquare2);
                }catch(MapException mapExcpetion){
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                    
                } catch (NoTargetInSquare noTargetInSquare) {
                    
                    gameModel.setErrorMessage("ERROR: IN ONE OF SQUARES THERE ARE NOT PLAYERS");
                    
                } catch (NotInSameDirection notInSameDirection) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARES ARE NOT IN THE SAME DIRECTION");
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE FIRST CHOSEN SQUARE ARE NOT AT ONE MOVE FROM YOU OR" +
                            "THE SECOND CHOSEN SQUARE ARE NOT ONE MOVE FROM THE FIRST");
                }
                break;
        }
    }
    
    public void PowerGlove(GameModel gameModel, PowerGlove weapon,RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase ;
        Player targetRocket1;
        Player targetRocket2;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseMode(map,currentPlayer,targetBase);
                    
                } catch (MapException mapExcpetion) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                    
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT EXACTLY AT ONE MOVE FROM YOU");
                }
                break;
            case RocketFistMode:
                
                try{
                    
                    targetRocket1 = gameModel.getPlayerById(view.getTarget2());
                    if(view.getTarget3()==-1){
                        
                        weapon.rocketFistMode(map,currentPlayer,targetRocket1);
                    }else{
                        
                        targetRocket2 = gameModel.getPlayerById(view.getTarget3());
                        weapon.rocketFistMode(map,currentPlayer,targetRocket1,targetRocket2);
                    }
                }catch (MapException mapException){
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE FIRST CHOSEN SQUARE ARE NOT AT ONE MOVE FROM YOU OR" +
                            "THE SECOND CHOSEN SQUARE ARE NOT ONE MOVE FROM THE FIRST");
                } catch (NotInSameDirection notInSameDirection) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARES ARE NOT IN THE SAME DIRECTION");
                }
                break;
        }
        
    }
    
    
    public void Sledgehammer(GameModel gameModel, Sledgehammer weapon,RemoteView view) throws RemoteException{
        
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase ;
        Player targetPulverize;
        Square destSquare;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseMode(map,currentPlayer,targetBase);
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                    
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE");
                }
                
                break;
            
            case PulverizeMode:
                
                try{
                    
                    destSquare = map.getSquare(view.getRow(),view.getColumn());
                    targetPulverize=gameModel.getPlayerById(view.getTarget1());
                    weapon.pulverizeMode(map,currentPlayer,targetPulverize,destSquare);
                } catch (NotInSameDirection notInSameDirection) {
                    
                    gameModel.setErrorMessage("ERROR: YOU ARE NOT MOVING THE TARGET IN ONE DIRECTION");
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE OR YOU ARE NOT MOVING THE " +
                            "TARGET ZERO, ONE OR TWO SQUARE");
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                    
                }
                
                break;
        }
        
    }
    
    
}
