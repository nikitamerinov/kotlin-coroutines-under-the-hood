import {makeScene2D, Txt} from '@motion-canvas/2d';
import {waitFor} from '@motion-canvas/core';

export default makeScene2D(function* (view) {
  view.add(<Txt text={'TBD'} />);

  yield* waitFor(1);
});
