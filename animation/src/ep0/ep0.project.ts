import {makeProject} from '@motion-canvas/core';

import intro from './scenes/intro?scene';
import agenda from './scenes/agenda?scene';
import outOfScope from './scenes/outOfScope?scene';
import inScope from './scenes/inScope?scene';
import nextEpisode from './scenes/nextEpisode?scene';
import outro from './scenes/outro?scene';
import otherNotes from './scenes/otherNotes?scene';
import voice from '../../assets/audio/ep0.wav';

// noinspection JSUnusedGlobalSymbols
export default makeProject({
  scenes: [
    intro,
    agenda,
    inScope,
    outOfScope,
    otherNotes,
    nextEpisode,
    outro
  ],
  audio: voice
});
