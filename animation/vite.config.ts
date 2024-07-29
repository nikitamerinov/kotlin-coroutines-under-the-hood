import {defineConfig} from 'vite';
import motionCanvas from '@motion-canvas/vite-plugin';
import ffmpeg from '@motion-canvas/ffmpeg';

// noinspection JSUnusedGlobalSymbols
export default defineConfig({
  plugins: [
    motionCanvas({
      project: ['./src/ep*/ep*.project.ts']
    }),
    ffmpeg(),
  ],
});
