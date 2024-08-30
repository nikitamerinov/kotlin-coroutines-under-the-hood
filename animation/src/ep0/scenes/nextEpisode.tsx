import {makeScene2D, Txt} from '@motion-canvas/2d';
import {createRef, Direction, Origin, slideTransition, waitFor} from '@motion-canvas/core';
import {Caption, Text} from '../../common';

export default makeScene2D(function* (view) {
  const title = createRef<Txt>();

  view.add(
    <>
      <Caption ref={title} text={'Next'} />
      <Caption text={'Coroutine fundamentals'} />
    </>
  );
  title().top(view.getOriginDelta(Origin.Top).addY(90));

  yield* slideTransition(Direction.Right);

  yield* waitFor(1);
});
