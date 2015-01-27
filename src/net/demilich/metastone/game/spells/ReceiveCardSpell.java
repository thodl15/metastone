package net.demilich.metastone.game.spells;

import net.demilich.metastone.game.GameContext;
import net.demilich.metastone.game.Player;
import net.demilich.metastone.game.cards.Card;
import net.demilich.metastone.game.entities.Entity;
import net.demilich.metastone.game.spells.desc.SpellArg;
import net.demilich.metastone.game.spells.desc.SpellDesc;

public class ReceiveCardSpell extends Spell {

	public static SpellDesc create(Card card) {
		return create(TargetPlayer.SELF, card);
	}

	public static SpellDesc create(TargetPlayer targetPlayer, Card... cards) {
		SpellDesc desc = new SpellDesc(ReceiveCardSpell.class);
		desc.set(SpellArg.CARDS, cards);
		desc.setTargetPlayer(targetPlayer);
		return desc;
	}

	@Override
	protected void onCast(GameContext context, Player player, SpellDesc desc, Entity target) {
		Card[] cards = (Card[]) desc.get(SpellArg.CARDS);
		TargetPlayer targetPlayer = desc.getTargetPlayer();

		Player opponent = context.getOpponent(player);
		switch (targetPlayer) {
		case BOTH:
			receiveCards(context, player.getId(), cards);
			receiveCards(context, opponent.getId(), cards);
			break;
		case OPPONENT:
			receiveCards(context, opponent.getId(), cards);
			break;
		case SELF:
			receiveCards(context, player.getId(), cards);
			break;
		}
	}
	
	private void receiveCards(GameContext context, int playerId, Card[] cards) {
		for (Card card : cards) {
			context.getLogic().receiveCard(playerId, card.clone());
		}
		
	}

}