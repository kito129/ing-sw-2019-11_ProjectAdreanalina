package it.polimi.controller;

import it.polimi.model.*;
import it.polimi.model.Exception.*;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.Weapon.*;
import it.polimi.view.RemoteView;
import javafx.print.PageLayout;

import java.lang.annotation.Target;
import java.lang.management.PlatformLoggingMXBean;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The type Weapon controller.
 */
public class WeaponController {
    
    private FunctionController functionController;
    private FunctionModel functionModel;

    /**
     * Instantiates a new Weapon controller.
     *
     * @param functionController the function controller
     */
    public WeaponController(FunctionController functionController){
        
        this.functionController = functionController;
        this.functionModel = functionController.functionModel;
        
    }
    
    /**
     * Select weapon.
     *
     * @param view the view
     * @throws RemoteException the remote exception
     */
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
                    case "LOCK RIFLE":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.LockRifle);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().add(WeaponsEffect.BaseEffect);
                        }
                        break;
                    case "ELECTOSCYTHE":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Electroscythe);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    //TODO
                    case "MACHINE GUN":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.MachineGun);
                        //set the effect list
                        //TODO
                        break;
                    case "TRACTOR BEAM":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.TractorBeam);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "VORTEX CANNON":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.VortexCannon);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().add(WeaponsEffect.BaseEffect);
                        }
                        break;
                    case "T.H.O.R":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Thor);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().add(WeaponsEffect.BaseEffect);
                        }
                        break;
                    case "FURNACE":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Furnace);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "PLASMA GUN":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.PlasmaGun);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "HEAT SEEKER":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Heatseeker);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().add(WeaponsEffect.BaseEffect);
                        }
                        break;
                    case "WHISPER":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Whisper);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().add(WeaponsEffect.BaseEffect);
                        }
                        break;
                    case "HELLION":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Hellion);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "FLAME THROWER":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Flamethrower);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "ZX-2":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Zx2);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "GRENADE LAUNCHER":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.GrenadeLauncher);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().add(WeaponsEffect.BaseEffect);
                        }
                        break;
                    case "SHOTGUN":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Shotgun);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "ROCKET LAUNCHER":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.RocketLauncher);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "POWER GLOVE":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.PowerGlove);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "RAILGUN":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Railgun);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "SHOCKWAVE":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Shockwave);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
                        break;
                    case "CYBERBLADE":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Cyberblade);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().add(WeaponsEffect.BaseEffect);
                            this.functionModel.getGameModel().getAvailableEffect().add(WeaponsEffect.ShadowstepEffect);
                        }
                        break;
                    case "SLADGEHAMMER":
                        
                        this.functionModel.getGameModel().setWeaponSelected(weapon);
                        this.functionModel.getGameModel().setWeaponName(weapon.getNameWeaponCard());
                        gameModel.setWeaponState(WeaponState.Sledgehammer);
                        //set the effect list
                        if (functionModel.getGameModel().getAvailableEffect().size()==0) {
                            this.functionModel.getGameModel().getAvailableEffect().addAll(weapon.getWeaponEffects());
                        }
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
    
    
    /**
     * Select weapon effect.
     *
     * @param view the view
     * @throws RemoteException the remote exception
     */
    public void selectWeaponEffect(RemoteView view) throws RemoteException {
        
        GameModel gameModel= this.functionModel.getGameModel();
        int i;
        
        i = view.getIndex2();
        if (i == -1) {
            
           
            
            try {
                getCorrectWeapon(gameModel.getWeaponName()).setCharge(false);
                
            } catch (NotValidInput notValidInput) {
               
                functionController.setErrorState("UNABLE TO SET ARMY TO UN-CHARGE");
            }
            view.resetInput();
            functionController.resetParameterWeapon();
            gameModel.setState(State.MENU);
            
        } else {
            
            if (gameModel.getAvailableEffect().get(i) != null) {
                
                //TODO CONTROLLARE PERHCE FACEVO QUESTO
                //functionModel.getGameModel().getPlayerMarked().removeAll(functionModel.getGameModel().getPlayerMarked());
                //functionModel.getGameModel().getPlayerDamaged().removeAll(functionModel.getGameModel().getPlayerDamaged());
                gameModel.setActualWeaponEffect(gameModel.getAvailableEffect().get(i));
            } else {
                
                functionController.setErrorState("NOT VALID INPUT");
            }
            switch (gameModel.getActualWeaponEffect()){
                
                case BaseEffect:
                    gameModel.setState(State.SELECTSHOOTINPUT);
                    break;
                case BaseMode:
                    gameModel.setState(State.SELECTSHOOTINPUT);
                    break;
                case CozyFireMode:
                    gameModel.setState(State.SELECTSHOOTINPUT);
                    break;
                case PhaseGlideEffect:
                    gameModel.setState(State.SELECTSHOOTINPUT);
                    break;
                case ScannerMode:
                    gameModel.setState(State.SELECTSHOOTINPUT);
                    break;
                case LongBarrelMode:
                    gameModel.setState(State.SELECTSHOOTINPUT);
                    break;
                case PiercingMode:
                    gameModel.setState(State.SELECTSHOOTINPUT);
                    break;
                case ShadowstepEffect:
                    gameModel.setState(State.SELECTSHOOTINPUT);
                    break;
                
                default:
                    gameModel.setState(State.PAYEFFECT);
                    break;
            }
            
        }
    }
    
    /**
     * Gets correct weapon.
     *
     * @param weaponName the weapon name
     * @return the correct weapon
     * @throws NotValidInput the not valid input
     */
    public WeaponCard getCorrectWeapon(String weaponName) throws NotValidInput {
        
        for (WeaponCard a: this.functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerWeapons()){
            
            if(a.getNameWeaponCard().equals(weaponName)){
                
                return a;
            }
        }
        throw  new NotValidInput();
    }
    
    
    public  void removeDeadPlayerFromMap(){
        
        for (Player a: functionModel.getGameModel().getPlayers(false,true)){
            
            if (!a.isAlive()){
    
                try {
                    functionModel.getGameModel().getMap().removePlayerFromSquare(a);
                    
                } catch (MapException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    /**
     * After shoot.
     *
     * @param view the view
     * @throws RemoteException the remote exception
     */
    public void afterShoot(RemoteView view) throws RemoteException {
    
        if (view.isBooleanChose() && view.getTarget1() != -1) {
    
            try {
        
        
                Player targetPlayer;
                //get input
        
                targetPlayer = this.functionModel.getGameModel().getPlayerById(view.getTarget1());
                TargetingScope targetingScope = functionController.getCorrectTargeting();
                //effect
                this.functionModel.usePowerUpTargetingScope(targetingScope, targetPlayer);
                functionModel.getGameModel().setMessageToAllView("CURRENT PLAYER " + functionModel.getGameModel().getActualPlayer().getName() + " USE POWER UP TARGETING SCOPE");
                functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().remove(this.functionModel.getGameModel().getPowerUpSelected());
        
            } catch (MapException e) {
                e.printStackTrace();
            }
    
            removeDeadPlayerFromMap();
    
            if (functionModel.getGameModel().getAvailableEffect().size() == 0) {
        
                try {
                    getCorrectWeapon(functionModel.getGameModel().getWeaponName()).setCharge(false);
            
                } catch (NotValidInput notValidInput) {
            
                    functionController.setErrorState("UNABLE TO SET ARMY TO UN-CHARGE");
                }
                //there are no more effect avaible
                functionController.resetParameterWeapon();
                view.resetInput();
                functionModel.getGameModel().incrementActionCount();
                functionModel.getGameModel().setState(State.MENU);
            } else {
        
                functionModel.getGameModel().setState(State.SELECTEFFECT);
            }
    
        }
    }
    
    /**
     * Pay weapon extra cost.
     *
     * @param view the view
     * @throws RemoteException the remote exception
     */
    public void payWeaponExtraCost(RemoteView view) throws RemoteException {
        
        GameModel gameModel = this.functionModel.getGameModel();
        ArrayList<EnumColorCardAndAmmo> cost = new ArrayList<>();
        
        try {
            switch (this.functionModel.getGameModel().getWeaponState()){
                
                case LockRifle:
                    
                    LockRifle lockRifle = (LockRifle) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        
                        //nothing to do
                        
                        //I effect
                        case BaseEffect :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case SecondLockEffect :
                            
                            //TO PAY AMMO
                            cost.addAll(lockRifle.getSecondLockCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                        
                    }
                    break;
                
                case Electroscythe:
                    
                    Electroscythe electroscythe = (Electroscythe) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        
                        //nothing to do
                        
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case ReaperMode :
                            
                            //TO PAY AMMO
                            cost.addAll(electroscythe.getReaperModeCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                        
                    }
                    break;
                
                //TODO
                case MachineGun:
                    
                    MachineGun machineGun = (MachineGun) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case FocusShotEffect :
                            
                            //TO PAY AMMO
                            cost.addAll(machineGun.getFocusShotCost());
                            functionController.selectRecharge(view,2,cost);
                            
                            break;
                        //III effect
                        case TurretTripodEffect :
                            
                            //TO PAY AMMO
                            cost.addAll(machineGun.getTurretTripodCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                        
                    }
                    break;
                
                case TractorBeam:
                    
                    TractorBeam tractorBeam = (TractorBeam) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case PunisherMode :
                            
                            //TO PAY AMMO
                            cost.addAll(tractorBeam.getPunisherModeCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                        
                    }
                    break;
                
                case Thor:
                    
                    Thor thor =(Thor) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case ChainReactionEffect :
                            
                            //TO PAY AMMO
                            cost.addAll(thor.getChainReactionCost());
                            functionController.selectRecharge(view,2,cost);
                            
                            break;
                        //III effect
                        case HighVoltageEffect :
                            
                            //TO PAY AMMO
                            cost.addAll(thor.getHighVoltageCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                        
                    }
                    break;
                
                case VortexCannon:
                    
                    VortexCannon vortexCannon = (VortexCannon) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case BlackHoleEffect :
                            
                            //TO PAY AMMO
                            cost.addAll(vortexCannon.getBlackHoleCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                        
                        
                    }
                    break;
                
                case Furnace:
                    
                    Furnace furnace = (Furnace) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case CozyFireMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        
                    }
                    break;
                
                case PlasmaGun:
                    
                    PlasmaGun plasmaGun = (PlasmaGun) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        
                        //II effect
                        case PhaseGlideEffect :
                            
                            //TO PAY AMMO
                            cost.addAll(plasmaGun.getPhaseGlideCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                        
                        
                        //III effect
                        case BaseEffectPlusChargedShotEffect :
                            
                            //to pay if boolean choise is true
                            //TO PAY AMMO
                            if (view.isBooleanChose()) {
                                
                                cost.addAll(plasmaGun.getChargedShotCost());
                                functionController.selectRecharge(view, 2, cost);
                            } else {
                                
                                functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            }
                            break;
                        
                        
                    }
                    break;
                
                case Heatseeker:
                    
                    Heatseeker heatseeker = (Heatseeker) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    
                    //nothing
                    functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                    
                    break;
                
                
                case Whisper:
                    
                    Whisper whisper = (Whisper) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    
                    //nothing
                    functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                    
                    break;
                
                case Hellion:
                    
                    Hellion hellion = (Hellion) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case NanoTracerMode :
                            
                            //TO PAY AMMO
                            cost.addAll(hellion.getNanoTracerModeCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                        
                        
                    }
                    break;
                
                case Flamethrower:
                    
                    Flamethrower flamethrower = (Flamethrower) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case BarbecueMode :
                            
                            //TO PAY AMMO
                            cost.addAll(flamethrower.getBarbecueModeCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                    }
                    break;
                
                case Zx2:
                    
                    Zx2 zx2 = (Zx2) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case ScannerMode :
                            
                            
                            //NO TO PAY
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        
                    }
                    break;
                
                case GrenadeLauncher:
                    
                    GrenadeLauncher grenadeLauncher = (GrenadeLauncher) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case ExtraGrenadeEffect :
                            
                            //TO PAY AMMO
                            cost.addAll(grenadeLauncher.getExtraGrenadeCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                        
                        case MoveTarget:
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        
                    }
                    break;
                
                case Shotgun:
                    
                    Shotgun shotgun = (Shotgun) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case LongBarrelMode :
                            
                            //NO TO PAY
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                    }
                    break;
                
                case RocketLauncher:
                    
                    RocketLauncher rocketLauncher = (RocketLauncher) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffectPlusFragmentingWarheadEffect :
                            
                            if(view.isBooleanChose()) {
                                //TO PAY AMMO
                                cost.addAll(rocketLauncher.getFragmentingWarheadCost());
                                functionController.selectRecharge(view, 2, cost);
                            } else {
                                
                                functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            }
                            break;
                        
                        //II effect
                        case RocketJumpEffect :
                            
                            //TO PAY AMMO
                            cost.addAll(rocketLauncher.getRocketJumpCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                    }
                    break;
                
                case PowerGlove:
                    
                    PowerGlove powerGlove = (PowerGlove) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case RocketFistMode :
                            
                            //TO PAY AMMO
                            cost.addAll(powerGlove.getRocketFistModeCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                        
                    }
                    break;
                
                case Railgun:
                    
                    Railgun railGun = (Railgun) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case PiercingMode :
                            
                            
                            //NO TO PAY
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        
                    }
                    break;
                
                case Shockwave:
                    
                    Shockwave shockwave = (Shockwave) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case TsunamiMode :
                            
                            //TO PAY AMMO
                            cost.addAll(shockwave.getTsunamiModeCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                    }
                    break;
                
                case Cyberblade:
                    
                    Cyberblade cyberblade = (Cyberblade) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffect :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case ShadowstepEffect :
                            
                            //NO TO PAY
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //III effect
                        case SliceAndDiceEffect :
                            
                            //TO PAY AMMO
                            cost.addAll(cyberblade.getSliceAndDiceCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                    }
                    break;
                
                case Sledgehammer:
                    
                    Sledgehammer sledgehammer = (Sledgehammer) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseMode :
                            
                            functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
                            
                            break;
                        //II effect
                        case PulverizeMode :
                            
                            //TO PAY AMMO
                            cost.addAll(sledgehammer.getPulverizeModeCost());
                            functionController.selectRecharge(view,2,cost);
                            break;
                    }
                    break;
            }
        } catch (NotValidInput notValidInput) {
            notValidInput.printStackTrace();
        }
        this.functionModel.getGameModel().setMessageToAllView("CURRENT PLAYER PAY EXTRA COST FOR : " + gameModel.getWeaponName() +" " +gameModel.getActualWeaponEffect() +" CORRECTLY");
        this.functionModel.getGameModel().setState(State.SELECTSHOOTINPUT);
        
    }
    
    /**
     * Select shoot input.
     *
     * @param view the view
     * @throws RemoteException the remote exception
     */
    public void selectShootInput(RemoteView view) throws RemoteException {
        
        GameModel gameModel = this.functionModel.getGameModel();
        ArrayList<Player> preDamaged = new ArrayList<>(functionModel.getGameModel().getPlayerDamaged());
        
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
                            
                            //TO PAY AMMO
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
                            
                            //TO PAY
                            this.ElectroscytheWeapon(gameModel,electroscythe,view);
                            
                            break;
                        
                    }
                    break;
                
                //TODO
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
                            
                            //TO PAY
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
                            
                            //TO PAY
                            this.Thor(gameModel,thor,view);
                            
                            break;
                        //III effect
                        case HighVoltageEffect :
                            
                            //TO PAY
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
                            
                            //TO PAY
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
                            
                            //NO to pay
                            this.Furnace(gameModel,furnace,view);
                            
                            break;
                        
                    }
                    break;
                
                case PlasmaGun:
                    
                    PlasmaGun plasmaGun = (PlasmaGun) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        
                        //II effect
                        case PhaseGlideEffect :
                            
                            this.PlasmaGun(gameModel,plasmaGun,view);
                            
                            break;
                        //III effect
                        case BaseEffectPlusChargedShotEffect :
                            
                            //to pay if boolean choise is true
                            this.PlasmaGun(gameModel,plasmaGun,view);
                            
                            break;
                        
                    }
                    break;
                
                case Heatseeker:
                    
                    Heatseeker heatseeker = (Heatseeker) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    
                    this.HeatSeeker(gameModel,heatseeker,view);
                    
                    break;
                
                
                case Whisper:
                    
                    Whisper whisper = (Whisper) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    
                    this.Whisper(gameModel,whisper,view);
                    
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
                            
                            //TO PAY
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
                            
                            
                            //TO PAY
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
                            
                            
                            //NO TO PAY
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
                            
                            this.GrenadeLauncher(gameModel,grenadeLauncher,view);
                            
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
                            
                            //NO TO PAY
                            this.Shotgun(gameModel,shotgun,view);
                            
                            break;
                    }
                    break;
                
                case RocketLauncher:
                    
                    RocketLauncher rocketLauncher = (RocketLauncher) functionController.weaponController.getCorrectWeapon(gameModel.getWeaponName());
                    switch(this.functionModel.getGameModel().getActualWeaponEffect()){
                        //I effect
                        case BaseEffectPlusFragmentingWarheadEffect :
                            
                            //TO PAY if BOOLEAN CHOISE
                            this.RocketLauncher(gameModel,rocketLauncher,view);
                            
                            break;
                        //II effect
                        case RocketJumpEffect :
                            
                            //TO PAY
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
                            
                            //TOPAY
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
                            
                            
                            //NO TO PAY
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
                            
                            //TO PAY
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
                            
                            //NO TO PAY
                            this.Cyberblade(gameModel,cyberblade,view);
                            
                            break;
                        //III effect
                        case SliceAndDiceEffect :
                            
                            //TO PAY
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
                            
                            //TO PAY
                            this.Sledgehammer(gameModel,sledgehammer,view);
                            
                            break;
                    }
                    break;
            }
        } catch (NotValidInput notValidInput) {
            notValidInput.printStackTrace();
        }
    
        //routine for grenade
        ArrayList<Player> postDamaged = new ArrayList<>(functionModel.getGameModel().getPlayerDamaged());
        if (preDamaged.size()<postDamaged.size()){
            
            postDamaged.removeAll(preDamaged);
            for (Player a: postDamaged){
                
                checkForGrenade(functionModel.getGameModel().getMap(),a,functionModel.getGameModel().getActualPlayer());
            }
        }
        
        if (gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps().contains(TargetingScope.class) && gameModel.getActualPlayer().getPlayerBoard().getAmmo().size()>0){
            
            gameModel.setWantToUseTargeting(true);
        } else {
    
            gameModel.setWantToUseTargeting(false);
        }
        
        this.functionModel.getGameModel().setMessageToAllView("CURRENT PLAYER USED: " + gameModel.getWeaponName() +" CORRECTLY");
        this.functionModel.getGameModel().setState(State.SHOOT);
        
    }
    
    public void checkForGrenade(Map map,Player damaged,Player current){
        
        
        if (map.isVisible(damaged, current)) {
    
            functionModel.getGameModel().getPlayerDamagedWithGrenadeVisibility().add(true);
        } else {
    
            functionModel.getGameModel().getPlayerDamagedWithGrenadeVisibility().add(false);
        }
    
        
    }

    //WEAPON

    /**
     * control Lock Rifle effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Lock Rifle.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    //Remove used effect
                    gameModel.getAvailableEffect().remove(WeaponsEffect.BaseEffect);
                    //add next effect
                    gameModel.getAvailableEffect().add(WeaponsEffect.SecondLockEffect);
                    
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (MapException e) {
                    
                    functionController.mapErrorGestor();
                }
               
                break;
            
            case SecondLockEffect:
                
                if(gameModel.getBeforeEffect()==WeaponsEffect.BaseEffect) {
                    
                    try {
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetSecondLock = gameModel.getPlayerById(view.getTarget2());
                        if (targetBase != targetSecondLock) {
                            
                            weapon.secondLockEffect(map, currentPlayer, targetSecondLock);
                            //empty list
                            gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
                        } else {
                            
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getSecondLockCost());
                        functionController.setErrorState("ERROR: THE CHOSEN TARGET IS THE SAME AS THE BASE EFFECT!!!");
                    } catch (NotVisibleTarget notVisibleTarget) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getSecondLockCost());
                        functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                    } catch (MapException e) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getSecondLockCost());
                        functionController.mapErrorGestor();
                    }
                }else{
                    
                    functionController.mapErrorGestor();
                }
                break;
        }
    }

    /**
     * control Electroscythe effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Electroscythe.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
    public void ElectroscytheWeapon(GameModel gameModel, Electroscythe weapon, RemoteView view) throws RemoteException{
        
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try {
                    
                    weapon.baseMode(map,currentPlayer);
                } catch (NoTargetInSquare noTargetInSquare) {
                    
                    functionController.setErrorState("ERROR: NO TARGET IN YOUR SQUARE!!!");
                } catch (MapException mapException){
                    
                    functionController.setErrorState("ERROR: MAP ERROR");
                }
                break;
            case ReaperMode:
                
                try {
                    
                    weapon.reaperMode(map, currentPlayer);
                    
                } catch (NoTargetInSquare noTargetInSquare) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getReaperModeCost());
                    functionController.setErrorState("ERROR: NO TARGET IN YOUR SQUARE!!!");
                } catch (MapException mapException) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getReaperModeCost());
                    functionController.mapErrorGestor();
                }
                
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control Machine Gun effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Machine Gun.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
    //TODO andre
    public void MachineGun(GameModel gameModel, MachineGun weapon, RemoteView view) throws RemoteException{

        Player currentPlayer = gameModel.getActualPlayer();
        Map map = gameModel.getMap();
        Player target1Base;
        Player target2Base;
        Player targetFocusShot;//da usare solo se ha scelto due barsagli inizialmente
        Player targetTurretTripod;
        int choise=-1; //chiedere questo intero;
        ArrayList<Player> targetBase = new ArrayList<>();

        switch (gameModel.getActualWeaponEffect()){

            case BaseEffect:
                //base mode
                try {

                    if (view.getTarget2()==-1) {

                        target1Base = gameModel.getPlayerById(view.getTarget1());
                        targetBase.add(target1Base);
                        weapon.baseEffect(map, currentPlayer, targetBase);
                      
                    }else {

                        target1Base = gameModel.getPlayerById(view.getTarget1());
                        target2Base = gameModel.getPlayerById(view.getTarget2());
                        if(target1Base!=target2Base) {

                            targetBase.add(target1Base);
                            targetBase.add(target2Base);
                            weapon.baseEffect(map, currentPlayer, targetBase);
                           
                        }else {

                            throw new NotValidInput();
                        }
                    }
                } catch (NotVisibleTarget notVisibleTarget) {

                    //gestione target 1 o 2 effetto base non visibili con il curretnPLayer
                } catch (NotValidInput notValidInput) {

                    //gestione target 1==target 2 effetto base
                } catch (MapException e) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;

            case FocusShotEffect:

                try {

                    if (view.getTarget2() == -1) {

                        target1Base = gameModel.getPlayerById(view.getTarget1());
                        weapon.focusShotEffect(currentPlayer,target1Base);
                    }else{

                        target1Base = gameModel.getPlayerById(view.getTarget1());
                        target2Base = gameModel.getPlayerById(view.getTarget2());
                        targetFocusShot=gameModel.getPlayerById(view.getTarget3());
                        if(targetFocusShot==target1Base||targetFocusShot==target2Base){

                            weapon.focusShotEffect(currentPlayer,targetFocusShot);
                           
                        }

                        else{

                            throw new NotValidInput();
                        }
                    }
                }catch (MapException e) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                } catch (NotValidInput notValidInput) {

                    //target selezionato non è uguale ad uno dell effetto base
                }
                break;

            case TurretTripodEffect:

                try {
                    if (view.getTarget2() == -1) {

                        //prendo la choise int.
                        if (choise == 0) {

                            target1Base = gameModel.getPlayerById(view.getTarget1());
                            weapon.focusShotEffect(currentPlayer, target1Base);

                        } else if (choise == 1) {

                            target1Base = gameModel.getPlayerById(view.getTarget1());
                            targetTurretTripod = gameModel.getPlayerById(view.getTarget4());
                            if(targetTurretTripod!=target1Base) {
                                weapon.turretTripodEffect(currentPlayer, target1Base);
                                weapon.turretTripodEffect(map, currentPlayer, targetTurretTripod);
                               
                            }else{

                                throw new NotValidInput();
                            }
                        }else if(choise==2){

                            targetTurretTripod = gameModel.getPlayerById(view.getTarget4());
                            weapon.turretTripodEffect(map,currentPlayer,targetTurretTripod);
                        }
                    }else{
                        
                        if (choise == 0) {

                            target1Base = gameModel.getPlayerById(view.getTarget1());
                            target2Base = gameModel.getPlayerById(view.getTarget2());
                            targetFocusShot=gameModel.getPlayerById(view.getTarget3());
                            if(targetFocusShot==target1Base) {
                                weapon.focusShotEffect(currentPlayer,target2Base);
                               
                            }else if(targetFocusShot==target2Base){
                                weapon.focusShotEffect(currentPlayer,target1Base);
                               
                            }
                        } else if (choise == 1) {

                            target1Base = gameModel.getPlayerById(view.getTarget1());
                            target2Base = gameModel.getPlayerById(view.getTarget2());
                            targetFocusShot=gameModel.getPlayerById(view.getTarget3());
                            targetTurretTripod = gameModel.getPlayerById(view.getTarget4());
                            if(targetTurretTripod!=target1Base) {

                                if(targetFocusShot==target1Base) {
                                    weapon.focusShotEffect(currentPlayer,target2Base);
                                }else if(targetFocusShot==target2Base){
                                    weapon.focusShotEffect(currentPlayer,target1Base);
                                }
                                weapon.turretTripodEffect(currentPlayer, target1Base);
                                weapon.turretTripodEffect(map, currentPlayer, targetTurretTripod);
                            }else{

                                throw new NotValidInput();
                            }
                        }else if(choise==2){

                            targetTurretTripod = gameModel.getPlayerById(view.getTarget4());
                            weapon.turretTripodEffect(map,currentPlayer,targetTurretTripod);
                        }
                    }
                }catch (MapException e) {

                   functionController.mapErrorGestor();
                } catch (NotVisibleTarget notVisibleTarget){

                    //terzo target non visibile
                } catch (NotValidInput notValidInput) {

                    //terzo target scelto non è diverso da uno dei due target dell'effetto base.
                }
        }
    }


    /**
     * control Tractor Beam effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Tractor Beam.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    
                }catch (NotVisibleTarget notVisibleTarget) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN SQUARE IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (NotValidDistance notValidDistance) {
                    
                    functionController.setErrorState("ERROR: YOU CAN'T MOVE YOUR TARGET FOR MORE THAN TWO MOVEMENTS!!!");
                } catch (MapException mapException) {
                    
                    functionController.mapErrorGestor();
                }
                break;
            
            case PunisherMode:
                
                try {
                    
                    targetBaseOrPunisher = gameModel.getPlayerById(view.getTarget1());
                    weapon.punisherMode(map, currentPlayer, targetBaseOrPunisher);
                   
                } catch (NotValidDistance notValidDistance) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getPunisherModeCost());
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS MORE THAN TWO MOVEMENTS FROM YOU!!!");
                } catch (MapException mapException) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getPunisherModeCost());
                    functionController.mapErrorGestor();
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control T.H.O.R. effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the T.H.O.R.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    //set before effect as base and remove
                    gameModel.setBeforeEffect(WeaponsEffect.BaseEffect);
                    
                    
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (MapException e) {
                    
                    functionController.mapErrorGestor();
                }
                gameModel.getAvailableEffect().remove(WeaponsEffect.BaseEffect);
                //add next effect
                gameModel.getAvailableEffect().add(WeaponsEffect.ChainReactionEffect);
                break;
            case ChainReactionEffect:
                
                if(gameModel.getBeforeEffect() == WeaponsEffect.BaseEffect) {
                    
                    try {
                        
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetChainReaction = gameModel.getPlayerById(view.getTarget2());
                        if ((map.isVisible(targetBase, targetChainReaction)) && (targetBase != targetChainReaction)) {
                            
                            weapon.chainReactionEffect(currentPlayer, targetChainReaction);
                            gameModel.setBeforeEffect(WeaponsEffect.ChainReactionEffect);
                           
                            
                        } else {
                            
                            functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getChainReactionCost());
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getChainReactionCost());
                        functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM THE TARGET CHOSEN IN THE " +
                                "BASE EFFECT OR THE CHOSEN TARGET IS THE SAME OF BASE EFFECT!!!");
                    } catch (MapException e) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getChainReactionCost());
                        functionController.mapErrorGestor();
                    }
                    //remove
                    gameModel.getAvailableEffect().remove(WeaponsEffect.ChainReactionEffect);
                    //add next effect
                    gameModel.getAvailableEffect().add(WeaponsEffect.HighVoltageEffect);
                }else {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getChainReactionCost());
                    functionController.setErrorState("ERROR: YOU CAN'T USE CHAIN REACTION EFFECT IF YOU DON'T USE THE BASE MODE!!!");
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
                            //empty list
                            gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
                            
                        } else {
                            
                            functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getHighVoltageCost());
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getHighVoltageCost());
                        functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM THE TARGET CHOSEN IN " +
                                "CHAIN REACTION EFFECT OR THE CHOSEN TARGET IS THE SAME OF BASE/CHAIN REACTION EFFECT!!!");
                    } catch (MapException e) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getHighVoltageCost());
                        functionController.mapErrorGestor();
                    }
                } else {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getHighVoltageCost());
                    functionController.setErrorState("ERROR: YOU CAN'T USE HIGH VOLTAGE EFFECT IF YOU DON'T USE CHAIN REACTION EFFECT!!!");
                }
                break;
        }
    }

    /**
     * control Vortex Cannon effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Vortex Cannon.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (NotValidDistance notValidDistance) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN SQUARE IN NOT AT LEAST ONE MOVEMENT FROM YOU " +
                            "OR THE CHOSEN TARGET IS NOT IN THE VORTEX SQUARE OR HE IS NOT DISTANCE ONE MOVEMENT FROM THE VORTEX SQUARE!!!");
                } catch (MapException mapException) {
                    
                    functionController.setErrorState("ERROR: MAP ERROR");
                }
                //remove
                gameModel.getAvailableEffect().remove(WeaponsEffect.BaseEffect);
                //add next effect
                gameModel.getAvailableEffect().add(WeaponsEffect.BlackHoleEffect);
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
                                
                                functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getBlackHoleCost());
                                throw new NotValidInput();
                            }
                        } else {
                            
                            target2BlackHole = gameModel.getPlayerById(view.getTarget3());
                            if ((target1BlackHole != target2BlackHole) && (target1BlackHole != targetBase) && (target2BlackHole != targetBase)) {
                                
                                targetsBlackHole=new ArrayList<>();
                                targetsBlackHole.add(target1BlackHole);
                                targetsBlackHole.add(target2BlackHole);
                                weapon.blackHoleEffect(map, vortexSquare, currentPlayer, targetsBlackHole);
                                //empty list
                                gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
                               
                            } else {
                                
                                functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getBlackHoleCost());
                                throw new NotValidInput();
                            }
                        }
                        
                    } catch (NotValidDistance notValidDistance) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getBlackHoleCost());
                        functionController.setErrorState("THE CHOSEN TARGET IS NOT IN THE VORTEX SQUARE OR HE IS NOT " +
                                "DISTANCE ONE MOVEMENT FROM THE VORTEX SQUARE!!!");
                    } catch (NotValidInput notValidInput) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getBlackHoleCost());
                        functionController.setErrorState("ONE OR TWO OF THE CHOSEN TARGETS ARE THE SAME OF THE BASE EFFECT " +
                                "OR THEY ARE EQUAL BETWEEN THEM!!!");
                    } catch (MapException e) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getBlackHoleCost());
                        functionController.mapErrorGestor();
                    }
                    //empty list
                    gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
                } else {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getBlackHoleCost());
                    functionController.setErrorState("ERROR: YOU CAN'T USE BLACK HOLE EFFECT IF YOU DON'T USE THE BASE EFFECT!!!");
                }
                break;
        }
        
    }

    /**
     * control Furnace effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Furnace.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.setErrorState("ERROR: YOU CAN'T CHOOSE YOUR ROOM!!!");
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN ROOM IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (MapException mapException) {
                    
                    functionController.mapErrorGestor();
                } catch (NoTargetInSquare noTargetInSquare) {

                    //todo no target in selected room
                }
                break;
            
            case CozyFireMode:
                
                try {
                    
                    targetSquareCozy = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.cozyFireMode(map, currentPlayer, targetSquareCozy);
                } catch (NotValidDistance notValidDistance) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN SQUARE IN NOT EXACTLY ONE MOVEMENT FROM YOU!!!");
                } catch (MapException mapException) {
                    
                    functionController.mapErrorGestor();
                } catch (NoTargetInSquare noTargetInSquare) {

                    //todo no target in targetsquare.
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control Plasma Gun effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Plasma Gun.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    gameModel.setBeforeEffect(WeaponsEffect.BaseEffectPlusChargedShotEffect);
                  
                    
                } catch (NotVisibleTarget notVisibleTarget) {
                    if(view.isBooleanChose()) {
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getChargedShotCost());
                    }
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (MapException e) {
                    
                    if(view.isBooleanChose()) {
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getChargedShotCost());
                    }
                    functionController.setErrorState("ERROR: MAP ERROR");
                }
                gameModel.getAvailableEffect().remove(WeaponsEffect.BaseEffectPlusChargedShotEffect);
                break;
            
            case PhaseGlideEffect:
                
                
                try {
                    
                    destSquarePhaseGlide = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.phaseGlideEffect(map, destSquarePhaseGlide, currentPlayer);
                   
                } catch (MapException e) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getPhaseGlideCost());
                    functionController.setErrorState("ERROR: MAP ERROR");
                } catch (NotValidDistance notValidDistance) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getPhaseGlideCost());
                    functionController.setErrorState("ERROR: YOU CAN MOVE YOUR TARGET ONLY ONE OR TWO MOVEMENTS!!!");
                }
                //remove effect
                gameModel.getAvailableEffect().remove(WeaponsEffect.PhaseGlideEffect);
                break;
        }
    }

    /**
     * control Heatseeker effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Heatseeker.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (MapException e) {
                    
                    functionController.setErrorState("ERROR: MAP ERROR");
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control Whisper effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Whisper.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
    public void Whisper(GameModel gameModel, Whisper weapon, RemoteView view) throws RemoteException {
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseEffect:
                try {
                    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, currentPlayer, targetBase);
                    
                } catch (NotValidDistance notValidDistance) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT AT LEAST TWO MOVEMENTS FROM YOUR POSITION!!!");
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (MapException e) {
                    
                    functionController.mapErrorGestor();
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control Hellion effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Hellion.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IN NOT AT LEAST ONE MOVEMENT FROM YOUR POSITION!!!" );
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (MapException mapException) {
                    
                    functionController.mapErrorGestor();
                }
                break;
            
            case NanoTracerMode:
                
                try {
                    
                    targetBaseOrTracer = gameModel.getPlayerById(view.getTarget1());
                    weapon.nanoTracerMode(map, currentPlayer, targetBaseOrTracer);
                   
                } catch (NotValidDistance notValidDistance) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getNanoTracerModeCost());
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IN NOT AT LEAST ONE MOVEMENT FROM YOUR POSITION!!!" );
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getNanoTracerModeCost());
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (MapException mapException) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getNanoTracerModeCost());
                    functionController.mapErrorGestor();
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control Flamethrower effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Flamethrower.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
    public void Flamethrower(GameModel gameModel, Flamethrower weapon,RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Player target2;
        Square targetSquare1;
        Square targetSquare2;
        
        switch (gameModel.getActualWeaponEffect()) {
            
            case BaseMode:
                
                try{
                    
                    targetBase=gameModel.getPlayerById(view.getTarget1());
                    if(view.getTarget2()==-1){
                        
                        weapon.baseMode(map,currentPlayer,targetBase);
                       
                    }else{
                        
                        target2 = gameModel.getPlayerById(view.getTarget2());
                        weapon.baseMode(map,currentPlayer,targetBase,target2);
                       
                    }
                    
                }catch (MapException mapException){
                    
                    functionController.mapErrorGestor();
                }catch (NotValidDistance notValidDistance){
                    
                    functionController.setErrorState("ERROR: THE FIRST CHOSEN TARGET IS NOT A DISTANCE MOVEMENT " +
                            "FROM YOU OR THE SECOND CHOSEN TARGET IS NOT A DISTANCE MOVEMENT FROM THE FIRST TARGET!!!");
                    
                }catch (NotInSameDirection notInSameDirection){
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGETS ARE NOT IN THE SAME DIRECTION!!!");
                    
                }
                break;
            case BarbecueMode:
                
                try{
                    
                    targetSquare1 = map.getSquare(view.getRow(),view.getColumn());
                    targetSquare2 = map.getSquare(view.getRow2(),view.getColumn2());
                    weapon.barbecueMode(map,currentPlayer,targetSquare1,targetSquare2);
                   
                }catch(MapException mapExcpetion){
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getBarbecueModeCost());
                    functionController.mapErrorGestor();
                } catch (NoTargetInSquare noTargetInSquare) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getBarbecueModeCost());
                    functionController.setErrorState("ERROR: IN ONE OF CHOSEN SQUARES THERE ARE NOT PLAYERS!!!");
                } catch (NotInSameDirection notInSameDirection) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getBarbecueModeCost());
                    functionController.setErrorState("ERROR: THE CHOSEN SQUARES ARE NOT IN THE SAME DIRECTION!!!");
                } catch (NotValidDistance notValidDistance) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getBarbecueModeCost());
                    functionController.setErrorState("ERROR: THE FIRST CHOSEN SQUARE IS NOT A DISTANCE MOVEMENT FROM YOU OR " +
                            "THE SECOND CHOSEN SQUARE IS NOT A DISTANCE MOVEMENT FROM THE FIRST SQUARE!!!");
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control ZX-2 effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the ZX-2.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (MapException e) {
                    
                    functionController.mapErrorGestor();
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
                    
                    functionController.setErrorState("ERROR: ONE OF CHOSEN TARGETS IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (NotValidInput notValidInput) {
                    
                    functionController.setErrorState("ERROR: TWO OR THREE OF THE CHOSEN TARGETS ARE EQUAL BETWEEN THEM!!!");
                } catch (MapException e) {
                    
                    functionController.mapErrorGestor();
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control Grenade Launcher effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Grenade Launcher.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                } catch (MapException e) {
                    
                   functionController.mapErrorGestor();
                }
                gameModel.getAvailableEffect().remove(WeaponsEffect.BaseEffect);
                gameModel.getAvailableEffect().add(WeaponsEffect.MoveTarget);
                gameModel.getAvailableEffect().add(WeaponsEffect.ExtraGrenadeEffect);
                break;
            
            case MoveTarget:
                
                if(gameModel.getBeforeEffect()==WeaponsEffect.BaseEffect) {
                    
                    try {
                        
                        destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        weapon.moveTarget(map, targetBase, destSquareBase);
                        gameModel.getAvailableEffect().remove(WeaponsEffect.MoveTarget);
                       
                    } catch (NotValidDistance notValidDistance) {
                        
                        functionController.setErrorState("ERROR: YOU CAN MOVE THE CHOSEN TARGET ONLY ONE MOVEMENT!!!");
                    } catch (MapException mapException) {
                        
                        functionController.mapErrorGestor();
                    }
                } else {
                    
                    functionController.setErrorState("ERROR: YOU CAN'T MOVE THE CHOSEN TARGET IF YOU DON'T USE THE BASE EFFECT!!!");
                }
                break;
            
            case ExtraGrenadeEffect:
                
                if(gameModel.getBeforeEffect() == WeaponsEffect.BaseEffect) {
                    
                    try {
                        
                        targetSquareExtra = gameModel.getMap().getSquare(view.getRow2(), view.getColumn2());
                        weapon.extraGrenadeEffect(map, currentPlayer, targetSquareExtra);
                        gameModel.getAvailableEffect().remove(WeaponsEffect.ExtraGrenadeEffect);
                       
                    } catch (NotVisibleTarget notVisibleTarget) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getExtraGrenadeCost());
                        functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                    } catch (MapException mapExcpetion) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getExtraGrenadeCost());
                        functionController.mapErrorGestor();
                    } catch (NoTargetInSquare noTargetInSquare) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getExtraGrenadeCost());
                        functionController.setErrorState("ERROR: NO TARGET IN THE CHOSEN SQUARE!!!");
                    }
                } else {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getExtraGrenadeCost());
                    functionController.setErrorState("ERROR: YOU CAN'T USE THE EXTRA GRENADE EFFECT IF YOU DON'T USE THE BASE EFFECT!!!");
                }
                break;
        }
    }

    /**
     * control Shotgun effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Shotgun.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.mapErrorGestor();
                }catch (NotValidDistance notValidDistance){
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE OR YOU ARE NOT " +
                            "MOVING EXACTLY ONE MOVEMENT THE TARGET!!!");
                }
                break;
            case LongBarrelMode:
                
                try{
                    
                    targetBase=gameModel.getPlayerById(view.getTarget1());
                    weapon.longBarrelMode(map,currentPlayer,targetBase);
                }catch (MapException mapException){
                    
                    functionController.mapErrorGestor();
                }catch (NotValidDistance notValidDistance){
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT EXACTLY AT ONE MOVEMENT FROM YOUR POSITION!!!");
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control Rocket Launcher effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Rocket Launcher.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    choice1 = view.isBooleanChose();
                    if(!choice1){
                        
                        destSquareBase = null;
                        weapon.baseEffect(map, targetBase, currentPlayer, destSquareBase);
                        
                        
                    }else{
                        
                        if ((view.getRow() != -1) && ((view.getColumn() != -1))) {
                            
                            destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                            weapon.baseEffectWithFragmenting(map, targetBase, currentPlayer, destSquareBase);
                           
                        } else {
                            
                            functionController.mapErrorGestor();
                        }
                    }
                    gameModel.getAvailableEffect().remove(WeaponsEffect.BaseEffectPlusFragmentingWarheadEffect);
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    choice1 = view.isBooleanChose();
                    if(!choice1){
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getFragmentingWarheadCost());
                    }
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE FROM YOUR POSITION!!!");
                    
                } catch (NotValidDistance notValidDistance) {
                    
                    choice1 = view.isBooleanChose();
                    if(!choice1){
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getFragmentingWarheadCost());
                    }
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS IN YOUR SQUARE OR YOU ARE NOT MOVING " +
                            "EXACTLY ONE MOVEMENT THE TARGET!!!");
                } catch (MapException mapExcpetion) {
                    
                    choice1 = view.isBooleanChose();
                    if(!choice1){
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getFragmentingWarheadCost());
                    }
                    functionController.mapErrorGestor();
                } catch (NoTargetInSquare noTargetInSquare){
                    
                    choice1 = view.isBooleanChose();
                    if(!choice1){
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getFragmentingWarheadCost());
                    }
                    functionController.setErrorState("ERROR: NO TARGET IN CHOSEN SQUARE!!!");
                }
                break;
            
            case RocketJumpEffect:
                
                try{
                    
                    destSquareJump = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.rocketJumpEffect(map,currentPlayer,destSquareJump);
                    gameModel.getAvailableEffect().remove(WeaponsEffect.RocketJumpEffect);
                }catch (NotValidDistance notValidDistance){
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getRocketJumpCost());
                   functionController.setErrorState("ERROR: YOU CAN MOVE ONLY ONE OR TWO MOVEMENTS!!!");
                }catch (MapException mapException){
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getRocketJumpCost());
                    functionController.mapErrorGestor();
                }
                gameModel.getAvailableEffect().remove(WeaponsEffect.RocketJumpEffect);
                break;
        }
    }

    /**
     * control Power Glove effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Power Glove.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.mapErrorGestor();
                    
                } catch (NotValidDistance notValidDistance) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT EXACTLY AT ONE MOVEMENT FROM YOUR POSITION!!!");
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
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getRocketFistModeCost());
                    functionController.mapErrorGestor();
                } catch (NotValidDistance notValidDistance) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getRocketFistModeCost());
                    functionController.setErrorState("ERROR: THE FIRST CHOSEN SQUARE IS NOT AT ONE MOVEMENT FROM YOUR " +
                            "POSITION OR THE SECOND CHOSEN SQUARE IS NOT AT ONE MOVEMENT FROM THE FIRST CHOSEN SQUARE!!!");
                } catch (NotInSameDirection notInSameDirection) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getRocketFistModeCost());
                    functionController.setErrorState("ERROR: THE CHOSEN SQUARES ARE NOT IN THE SAME DIRECTION!!!");
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control Rail Gun effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Rail Gun.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.setErrorState("ERROR: THE CHOSEN DIRECTION DOES NOT EXIST!!!");
                    
                } catch (NotInDirection notInDirection) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT IN CHOSEN CARDINAL DIRECTION!!!");
                } catch (MapException e) {
                    
                    functionController.mapErrorGestor();
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
                    
                    functionController.setErrorState("ERROR: THE CHOSEN DIRECTION DOES NOT EXIST!!!");
                } catch (NotInDirection notInDirection) {
                    
                    functionController.setErrorState("ERROR: ONE OF CHOSEN TARGET IS NOT IN CHOSEN CARDINAL DIRECTION!!!");
                } catch (MapException e) {
                    
                    functionController.mapErrorGestor();
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control Shockwave effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Shockwave.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGETS ARE NOT ALL DIFFERENT BETWEEN THEM OR " +
                            "EACH ONE ARE NOT IN A SQUARE EXACTLY AT ONE MOVEMENT FROM YOUR POSITION!!!");
                } catch (MapException e) {
                    
                    functionController.mapErrorGestor();
                }
                break;
            
            case TsunamiMode:
                
                try{
                    
                    ArrayList<Player> allPlayerInGame=new ArrayList<>(gameModel.getPlayers(false,false));
                    weapon.tsunamiMode(map,currentPlayer,allPlayerInGame);
                   
                }catch (NotValidDistance notValidDistance){
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getTsunamiModeCost());
                    functionController.setErrorState("ERROR: NO PLAYER AT ONE MOVEMENT FROM YOUR POSITION!!!");
                }
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }

    /**
     * control Cyberblade effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Cyberblade.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    gameModel.getAvailableEffect().remove(WeaponsEffect.BaseEffect);
                    gameModel.getAvailableEffect().add(WeaponsEffect.SliceAndDiceEffect);
                   
                    
                    
                } catch (NotValidDistance notValidDistance) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE!!!");
                } catch (MapException mapException) {
                    
                    functionController.mapErrorGestor();
                }
                break;
            case ShadowstepEffect:
                
                try{
                    
                    destSquareShadow = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.shadowstepEffect(map,currentPlayer,destSquareShadow);
                    gameModel.getAvailableEffect().remove(WeaponsEffect.ShadowstepEffect);
                }catch(NotValidDistance notValidDistance){
                    
                    functionController.setErrorState("ERROR: YOU CAN ONLY MOVE ONE MOVEMENT!!!");
                }catch (MapException mapException){
                    
                    functionController.mapErrorGestor();
                }
                
                break;
            case SliceAndDiceEffect:
                
                if(gameModel.getBeforeEffect()==WeaponsEffect.BaseEffect) {
                    
                    try {
                        
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetSliceAndDice = gameModel.getPlayerById(view.getTarget2());
                        if (targetBase != targetSliceAndDice) {
                            
                            weapon.sliceAndDiceEffect(map, currentPlayer, targetSliceAndDice);
                            gameModel.getAvailableEffect().remove(WeaponsEffect.SliceAndDiceEffect);
                           
                            
                        } else {
                            
                            functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getSliceAndDiceCost());
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getSliceAndDiceCost());
                        functionController.setErrorState("ERROR: THE CHOSEN TARGET IS THE SAME OF THE BASE EFFECT!!!");
                    } catch (NotValidDistance notValidDistance) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getSliceAndDiceCost());
                        functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE!!!");
                    } catch (MapException mapException) {
                        
                        functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getSliceAndDiceCost());
                        functionController.mapErrorGestor();
                    }
                    
                    break;
                } else {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getSliceAndDiceCost());
                    functionController.setErrorState("ERROR: YOU CAN'T USE SLICE AND DICE EFFECT IF YOU DON'T USE THE BASE EFFECT!!!");
                }
        }
    }

    /**
     * control Sledgehammer effect and set right parameters.
     *
     * @param gameModel the game model of the game.
     * @param weapon    the Sledgehammer.
     * @param view      the view.
     * @throws RemoteException the remote exception
     */
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
                    
                    functionController.mapErrorGestor();
                    
                } catch (NotValidDistance notValidDistance) {
                    
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE!!!");
                }
                
                break;
            
            case PulverizeMode:
                
                try{
                    
                    destSquare = map.getSquare(view.getRow(),view.getColumn());
                    targetPulverize=gameModel.getPlayerById(view.getTarget2());
                    weapon.pulverizeMode(map,currentPlayer,targetPulverize,destSquare);
                   
                } catch (NotInSameDirection notInSameDirection) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getPulverizeModeCost());
                    functionController.setErrorState("ERROR: YOU ARE NOT MOVING THE CHOSEN TARGET IN ONE CARDINAL DIRECTION!!!");
                } catch (NotValidDistance notValidDistance) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getPulverizeModeCost());
                    functionController.setErrorState("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE OR YOU ARE MOVING THE " +
                            "CHOSEN TARGET OF MORE THAN TWO MOVEMENTS!!!");
                } catch (MapException e) {
                    
                    functionModel.getGameModel().getActualPlayer().getPlayerBoard().getAmmo().addAll(weapon.getPulverizeModeCost());
                    functionController.mapErrorGestor();
                }
                
                break;
        }
        //empty list
        gameModel.getAvailableEffect().removeAll(gameModel.getAvailableEffect());
    }
}
