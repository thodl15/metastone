package net.demilich.metastone.game.cards.concrete.goblinsvsgnomes.rogue;

import net.demilich.metastone.game.cards.Rarity;
import net.demilich.metastone.game.cards.SpellCard;
import net.demilich.metastone.game.entities.heroes.HeroClass;
import net.demilich.metastone.game.spells.ComboSpell;
import net.demilich.metastone.game.spells.DestroySpell;
import net.demilich.metastone.game.spells.DestroyWeaponSpell;
import net.demilich.metastone.game.spells.MetaSpell;
import net.demilich.metastone.game.spells.desc.SpellDesc;
import net.demilich.metastone.game.targeting.EntityReference;
import net.demilich.metastone.game.targeting.TargetSelection;

public class Sabotage extends SpellCard {

	public Sabotage() {
		super("Sabotage", Rarity.EPIC, HeroClass.ROGUE, 4);
		setDescription("Destroy a random enemy minion. Combo: And your opponent's weapon.");
		
		SpellDesc destroyMinionSpell = DestroySpell.create();
		destroyMinionSpell.setTarget(EntityReference.ENEMY_MINIONS);
		destroyMinionSpell.pickRandomTarget(true);
		
		SpellDesc destroyWeaponSpell = DestroyWeaponSpell.create(); 
		destroyWeaponSpell.setTarget(EntityReference.ENEMY_HERO);
		
		SpellDesc comboSpell = MetaSpell.create(destroyMinionSpell, destroyWeaponSpell);
		comboSpell.setTarget(EntityReference.NONE);
		
		setSpell(ComboSpell.create(destroyMinionSpell, comboSpell));
		setTargetRequirement(TargetSelection.NONE);
	}



	@Override
	public int getTypeId() {
		return 572;
	}
}