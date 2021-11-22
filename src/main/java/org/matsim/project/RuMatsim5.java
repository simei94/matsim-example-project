package org.matsim.project;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.network.Link;
import org.matsim.contrib.otfvis.OTFVisLiveModule;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.QSimConfigGroup;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;

public class RuMatsim5 {



    public static void main(String args[]) {

        DummyAnalysis dummyAnalysis = new DummyAnalysis();

        Config config = ConfigUtils.loadConfig("scenarios/equil/config.xml");
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
        config.controler().setLastIteration(0);

        config.qsim().setTrafficDynamics(QSimConfigGroup.TrafficDynamics.kinematicWaves);
        config.qsim().setSnapshotStyle(QSimConfigGroup.SnapshotStyle.kinematicWaves);

        Scenario scenario = ScenarioUtils.loadScenario(config);

        for(Link link: scenario.getNetwork().getLinks().values()) {
            link.setFreespeed(1.);
        }

        Controler controler = new Controler(scenario);

        controler.addOverridingModule(new AbstractModule() {
            @Override
            public void install() {
                //THIS (ALTHOUGH BIND TO INSTANCE IS NOT RECOMMENDED!)
//                this.addEventHandlerBinding().toInstance(dummyAnalysis);
                //OR THIS
                this.addEventHandlerBinding().toInstance(new DummyAnalysis());
            }
        });
        controler.addOverridingModule(new OTFVisLiveModule());

        controler.run();
    }
    private static class DummyAnalysis implements LinkEnterEventHandler {

        @Override
        public void handleEvent(LinkEnterEvent event) {
            Id<Link> linkId = event.getLinkId();
            System.out.println("Link ID: " + linkId);

        }
    }
}
