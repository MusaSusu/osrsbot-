package net.runelite.client.plugins.OzonePanel.UI;


import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.OzonePanel.MusaConfig;
import net.runelite.client.plugins.OzonePanel.UI.Scripts.ScriptsPanel;
import net.runelite.client.plugins.OzonePanel.UI.Scripts.ScriptsPluginsListPanel;
import net.runelite.client.plugins.OzonePanel.UI.Tasks.TasksPanel;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.materialtabs.MaterialTab;
import net.runelite.client.ui.components.materialtabs.MaterialTabGroup;
import net.unethicalite.client.config.UnethicaliteConfig;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Singleton
public class MusaPanelRepo extends PluginPanel
{
    private final List<QuestsPanelContainer> containers = new ArrayList<>();

    private final JPanel display = new JPanel();
    private final MaterialTabGroup tabGroup = new MaterialTabGroup(display);

    private final QuestsPanel questsPanel;
    private final TasksPanel tasksPanel;
    private final ScriptsPanel scriptsPanel;

    @Inject
    public MusaPanelRepo(Client client,
                         MusaConfig config,
                         ConfigManager configManager,
                         TasksPanel tasksPanel,
                         QuestsPanel questsPanel,
                         ScriptsPanel scriptsPanel
    )
    {
        setLayout(new BorderLayout());
        tabGroup.setBorder(new EmptyBorder(5, 0, 0, 0));

        this.scriptsPanel = scriptsPanel;
        this.questsPanel = questsPanel;
        this.tasksPanel = tasksPanel;

        MaterialTab scriptsTab = new MaterialTab("Scripts", tabGroup, scriptsPanel);
        MaterialTab questsTab = new MaterialTab("Quests", tabGroup, questsPanel);
        MaterialTab tasksTab = new MaterialTab("Tasks", tabGroup, tasksPanel);

        //containers.add(tasksPanel);
        //containers.add(scriptsPanel);
        //containers.add(questsPanel);

        tabGroup.addTab(tasksTab);
        tabGroup.addTab(scriptsTab);
        tabGroup.addTab(questsTab);

        add(tabGroup, BorderLayout.NORTH);
        add(display, BorderLayout.CENTER);
    }

    @Subscribe
    private void onConfigChanged(ConfigChanged e)
    {
        if (!e.getGroup().equals(UnethicaliteConfig.CONFIG_GROUP))
        {
            return;
        }
        //SwingUtilities.invokeLater(() -> containers.forEach(QuestsPanelContainer::rebuild));
    }
}
