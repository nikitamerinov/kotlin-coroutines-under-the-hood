import {makeScene2D} from '@motion-canvas/2d';
import {Direction, slideTransition, waitFor} from '@motion-canvas/core';
import {Caption} from '../../common';

export default makeScene2D(function* (view) {
  view.add(
    <>
      <Caption text={'Other notes'} />
    </>
  );

  yield* slideTransition(Direction.Right);

  yield* waitFor(1);
});
