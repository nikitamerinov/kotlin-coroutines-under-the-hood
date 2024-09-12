import {Txt, withDefaults} from '@motion-canvas/2d';

export const Text = withDefaults(Txt, {
  fontFamily: 'JetBrains Mono',
  fill: '#d0d2d8'
});

export const Caption = withDefaults(Text, {
  fontSize: 80
})
