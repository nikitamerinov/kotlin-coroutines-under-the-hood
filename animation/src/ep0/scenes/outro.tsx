import {makeScene2D} from '@motion-canvas/2d';
import {Direction, slideTransition, waitFor, waitUntil} from '@motion-canvas/core';
import {Caption} from '../../common';

export default makeScene2D(function* (view) {
  view.add(
    <>
      <Caption text={'Thank you for watching'} />
    </>
  );

  yield* slideTransition(Direction.Right);

  yield* waitUntil('end');
});
