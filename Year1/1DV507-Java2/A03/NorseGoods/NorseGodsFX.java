package NorseGoods;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NorseGodsFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ArrayList<NorseGods> list = new ArrayList<>();
        list.add(new NorseGods("Loki","Giant","The sly, trickster of the Norse gods. Son of two giants. Also known as the Sly One, the Trickster, the Shape Changer and the Sky Traveller. Becomes increasingly more evil. He is responsible for the death of Balder. Bound until Ragnarok."));
        list.add(new NorseGods("Odin","Aesir","Odin (Óðinn in Old Norse), possibly the most revered yet enigmatic of all Norse gods, was regarded as the king of the Æsir tribe of gods. Historically, Odin had always been prominent in the pantheon of Germanic mythology, as is evident from Tacitus’ late 1st-century AD work Germania (where Odin is seen as the equivalent of Roman god Mercury). And given his mythical eminence over the cultural framework of the Germanic people, Odin was associated with various (and often antithetical) aspects, ranging from wisdom, healing, royalty to death, sorcery, and even frenzy.\n" +
                "\n" +
                "Pertaining to the attribute of wisdom, the character of Odin mirroring his ‘contradictory’ aspects, was often portrayed as the haggard wanderer who relentlessly seeks knowledge, in spite of his regal status as the ruler of the Asgard. One of the stories epitomizes the god’s thirst for knowledge and wisdom where Odin willingly gouges out one of his eyes as a sacrifice for Mimir, a shadowy being who possesses unparalleled knowledge because of its consumption of the water from the Well of Urd. Mimir in return offers Odin a draught from the well that draws water from the roots of the Yggdrasil, the cosmic tree that binds the nine worlds of Norse mythology.\n" +
                "\n" +
                "On the other hand, the one-eyed Odin also has a sinister (albeit in a nascent level) side to him, given the entity’s proclivity for provocation that leads to conflicts and wars. Essentially, when perceived as a war-god, Odin was seen as the epitome of battle frenzy and chaos – aspects that were favored by the warlords and berserkers. In any case, in our modern day context, the name of Odin is related to Wednesday, since the word is derived from wodnesdæg (“Woden’s Day”), with Odin being referred to as Wōden in Old English and Wōtan in Old High German."+
                "\n\n\n."));
        list.add(new NorseGods("Ymir ","Giant","Like with most mythologies, including Mesopotamian and Egyptian, the Norse pantheon had its primeval entity in the form of Ymir, the ancestor of all jötnar (mythic entities that ranged from giants to other fantastical creatures). Now as opposed to a strict categorization as one of the Norse gods, Ymir was perceived more as the ‘first being’ who was created by the ice of Niflheim combined with the heat of Muspelheim, long before the existence of the Earth. And after his own genesis, Ymir, with his hermaphroditic body, was responsible for birthing male, female, and other mythical beings, who in turn would go on to bear future generations.\n" +
                "\n" +
                "And mirroring other primeval deities of ancient mythologies, the narrative of Ymir took a turn, with the entity being given a tragic ending due to his apparent evil machinations. To that end, Buri (created after Ymir), often acknowledged as the first of the Norse gods, had a son named Bor, who finally married one of Ymir’s descendants Bestla, and their union produced three sons – Ve, Vili, and Odin. But the angry Ymir confronted these ascending young Norse gods, which eventually led to his own death at the hands of the three brothers.\n" +
                "\n" +
                "The three Norse gods, including Odin, then proceeded on to create the entire earth from Ymir’s fallen body, with his blood accounting for the seas and oceans, while his bones made up the rocks and mountains. Furthermore, his hair was used for the trees, his skull was transformed into the sky and heavens, and his brains were made into clouds. And finally, his eyebrows were fashioned into the Midgard – the ‘middle realm’ of mankind."));
        list.add(new NorseGods("Frigg","Aesir","Possibly the foremost of all Norse goddesses when it came to their pantheon, Frigg was regarded as the Queen of the Æsir and the goddess of the sky. Moreover, with her special status as the spouse of Odin, the deity, with her power of foreknowledge, was also frequently associated with fertility, household, motherhood, marriage, and even domestic matters. In essence, of all the Norse gods, it was the mythic aspects of Frigg that were mostly related to the perceived bliss of family life.\n" +
                "\n" +
                "On the other hand, Frigg’s love for family and motherly protectiveness over also leads to the tragic episode of her favored child Balder’s death (discussed later). Interestingly enough, in spite of Frigg’s eminence in later Norse mythology (during the Viking period), her Old Germanic version is steeped in mystery – a factor that is still debated in the academic world. Pertaining to the latter, according to one of the origin hypotheses, Frigg was possibly identified with the goddess Freyja (who is discussed later in the article) during the Proto-Germanic period. But as scholar Stephan Grundy mentioned in his book The Concept of the Goddess"));
        list.add(new NorseGods("Thor","Aesir","Arguably the most famous of the Norse gods, Thor (Þórr in Old Norse), the god of thunder, with his burly might and boisterous ways, epitomized the formidable warrior who was accorded high status in the Germanic society of ancient and early medieval times. Regarded as the son of Odin and his wife Fjörgyn (not to be confused with Frigg), Thor, with his red beard and eyes, was hailed as the loyal and stalwart defender of the Æsir’s stronghold of Asgard, thus suggesting his symbolic role as the protector of the ordered cosmos.\n" +
                "\n" +
                "Suffice it to say, according to Poetic Edda, Thor was considered as the strongest of all beings among both gods and men. And his strength was rather ‘amplified’ by some of his specially-crafted apparels, including his iron gloves and the belt of Megingjard (or megingjarðar in Old Norse). But the most common item associated with Thor undoubtedly pertains to the dwarf-crafted hammer Mjöllnir (roughly translated to ‘lightning’), thus alluding to how thunder was perceived (by the pre-Christianity era Norsemen) as the result of Thor striking his hammer, presumably when slaying giants and monsters while riding his chariot drawn by two giant goats – Tanngniost and Tanngrisnir.\n" +
                "\n" +
                "Interestingly enough, Thor was also regarded as the god of agriculture, fertility, and hallowing. Pertaining to the former, this aspect was probably an extension of Thor’s role as a sky god who was also responsible for rain. To that end, Thor’s wife Sif and her golden hair possibly symbolized the fields of grain, and thus their union embodied the fruitfulness and verdancy of the lands. As for our modern context, ‘Thursday’ is derived from Old English þurresdæg, a contraction (possibly influenced by Old Norse þorsdagr) of þunresdæg, which literally means “Thor’s day”."));
        list.add(new NorseGods("Balder","Aesir","Regarded as the Æsir god of light and purity, Balder, or Baldur (Baldr in Old Norse), the younger son of Odin and Frigg, and half-brother of Thor, epitomized the effulgent summer sun itself. He was also hailed as a fair, wise, and gracious divine being whose beauty even abashed the elegant flowers before him. Matching his physical attributes, his abode Breidablik in Asgard was considered the most exquisite of all halls in the stronghold of the Norse gods, flaunting its gilded silver components and embellished pillars that only allowed the purest of hearts to enter."+
                "\n" +
                "Balder also possessed the greatest ship ever built, Hringhorni, which was later used as the funeral pyre after the god’s tragic death. Relating to this deplorable incident, Balder was mistakenly killed by Höðr, Balder’s twin brother, who was also blind. Höðr was presented a dart made of mistletoe by the cunning Loki, who knew that Balder was impervious to all living elements on the face of Asgard and Midgard, except for the seemingly harmless mistletoe (which Frigg, Balder’s mother, missed when making a plea to most living things when it came to causing no harm to her delicate son). So the dart – thrown jokingly at Balder (as was the habit among the Norse gods), directly pierced his heart and slew him, instead of bouncing off the god. Consequently, many of the grief-stricken Norse gods even tried to bring back Balder from the domain of Hel, but to no avail, and thus the wise god was lost to them on account of Loki’s cruel prank."));
        list.add(new NorseGods("Tyr","Giant","The deity of war and heroic glory, Tyr (or Týr in Old Norse) was regarded as the bravest of the Norse gods of the Germanic people. And in spite of his association with wars – more specifically the formalities of conflict, including treaties, his origins are rather enigmatic, with the deity possibly being one the oldest and most important of the ancient Germanic pantheon, until he was supplanted by Odin (who had been described in many myths as Tyr’s father, while other stories place Tyr as the son of the giant Hymir). In any case, since some of the aspects of Tyr related to formalities, the god was also hailed as the deity of justice and oaths.\n" +
                "\n" +
                "Tyr was often depicted as the one-handed god since his limb was bitten off by the monstrous wolf Fenrir when the god tried to trap the creature (and Fenrir was thus successfully bound till Ragnarok due to the sacrifice of Tyr). In spite of this episode, Tyr is foretold to be slain by Garm, the guard dog of Hel, as opposed to Fenrir (according to the prose version of Ragnarök). And like some other Norse gods, Tyr also has his significance in our modern day context. In that regard, the Old English variant of his name is Tiw, and he was associated with Mars by the Romans, and thus dies Marti (Mars’ Day) came to be known as tiwesdæg (Tuesday)."));
        list.add(new NorseGods("Bragi","Other","Bragi (which roughly translates to ‘Poet’ in Old Norse), often considered as the skaldic god of poetry in Norse mythology, pertains to a unique mythical character who possibly shared traits with the historical 9th-century bard Bragi Boddason, who himself might have served in the courts of Ragnar Lodbrok and Björn at Hauge. In any case, when it came to legends, the god Bragi was perceived as the bard of Valhalla, the magnificent hall of Odin where all the fallen heroes and warriors are gathered for the ultimate ‘showdown’ at Ragnarok. To that end, Bragi was hailed as the skillful poet-god who sang and delighted the hordes of the Einherjar (warriors who died in battles and were brought to Odin’s majestic hall by the Valkyries)."+
                "\n"+
                "As for the Norse god’s historical counterparts, there are a few candidates other than Bragi Boddason mentioned in various Poetic Edda pieces. The names include Bragi son of Hálfdan the Old (mentioned in Skjáldskaparmál) and Bragi Högnason (mentioned in the second part of Helgakviða Hundingsbana)."));
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(5,5,5,5));

        //TextFlow description = new TextFlow();

        //pane.setCenter(description);
        ListView<String> lv = new ListView<String>();
        for (int i=0;i<list.size();i++)
            lv.getItems().add(list.get(i).getName());

        Text title = new Text("Norse Gods and other Beings");
        title.setFont(Font.font("Arial",FontWeight.BOLD,25));
        title.setTextAlignment(TextAlignment.CENTER);
        pane.setTop(title);
        pane.setLeft(lv);

        Scene scene = new Scene(pane,800,500);
        primaryStage.setTitle("Norse Gods");
        primaryStage.setScene(scene);
        primaryStage.show();

        lv.setOnMouseClicked(e->{
            int pos =lv.getSelectionModel().getSelectedIndex();
            String help = list.get(pos).getName()+"\n"+list.get(pos).getRace()+"\n\n\n"+list.get(pos).getDesc();
            Text name = new Text(list.get(pos).getName());
            name.setFont(Font.font("Arial",FontWeight.BOLD,20));
            Text race = new Text("\n"+list.get(pos).getRace());
            Text desc = new Text("\n\n"+list.get(pos).getDesc());

            TextFlow test = new TextFlow(name,race,desc);
            test.setTextAlignment(TextAlignment.LEFT);
            test.setMaxWidth(500);
            test.setMinWidth(500);
            test.setMinHeight(420);
            test.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            ScrollPane scroll = new ScrollPane(test);

            pane.setCenter(scroll);
            BorderPane.setMargin(scroll,new Insets(10,10,10,10));


        });
    }
}
